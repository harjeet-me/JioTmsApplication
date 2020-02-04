package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.FileSystem;
import com.jio.tms.v1.repository.FileSystemRepository;
import com.jio.tms.v1.repository.search.FileSystemSearchRepository;
import com.jio.tms.v1.service.FileSystemService;
import com.jio.tms.v1.service.dto.FileSystemDTO;
import com.jio.tms.v1.service.mapper.FileSystemMapper;
import com.jio.tms.v1.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.jio.tms.v1.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FileSystemResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class FileSystemResourceIT {

    private static final byte[] DEFAULT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATA_CONTENT_TYPE = "image/png";

    @Autowired
    private FileSystemRepository fileSystemRepository;

    @Autowired
    private FileSystemMapper fileSystemMapper;

    @Autowired
    private FileSystemService fileSystemService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.FileSystemSearchRepositoryMockConfiguration
     */
    @Autowired
    private FileSystemSearchRepository mockFileSystemSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFileSystemMockMvc;

    private FileSystem fileSystem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FileSystemResource fileSystemResource = new FileSystemResource(fileSystemService);
        this.restFileSystemMockMvc = MockMvcBuilders.standaloneSetup(fileSystemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileSystem createEntity(EntityManager em) {
        FileSystem fileSystem = new FileSystem()
            .data(DEFAULT_DATA)
            .dataContentType(DEFAULT_DATA_CONTENT_TYPE);
        return fileSystem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileSystem createUpdatedEntity(EntityManager em) {
        FileSystem fileSystem = new FileSystem()
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        return fileSystem;
    }

    @BeforeEach
    public void initTest() {
        fileSystem = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileSystem() throws Exception {
        int databaseSizeBeforeCreate = fileSystemRepository.findAll().size();

        // Create the FileSystem
        FileSystemDTO fileSystemDTO = fileSystemMapper.toDto(fileSystem);
        restFileSystemMockMvc.perform(post("/api/file-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileSystemDTO)))
            .andExpect(status().isCreated());

        // Validate the FileSystem in the database
        List<FileSystem> fileSystemList = fileSystemRepository.findAll();
        assertThat(fileSystemList).hasSize(databaseSizeBeforeCreate + 1);
        FileSystem testFileSystem = fileSystemList.get(fileSystemList.size() - 1);
        assertThat(testFileSystem.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testFileSystem.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);

        // Validate the FileSystem in Elasticsearch
        verify(mockFileSystemSearchRepository, times(1)).save(testFileSystem);
    }

    @Test
    @Transactional
    public void createFileSystemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileSystemRepository.findAll().size();

        // Create the FileSystem with an existing ID
        fileSystem.setId(1L);
        FileSystemDTO fileSystemDTO = fileSystemMapper.toDto(fileSystem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileSystemMockMvc.perform(post("/api/file-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileSystemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileSystem in the database
        List<FileSystem> fileSystemList = fileSystemRepository.findAll();
        assertThat(fileSystemList).hasSize(databaseSizeBeforeCreate);

        // Validate the FileSystem in Elasticsearch
        verify(mockFileSystemSearchRepository, times(0)).save(fileSystem);
    }


    @Test
    @Transactional
    public void getAllFileSystems() throws Exception {
        // Initialize the database
        fileSystemRepository.saveAndFlush(fileSystem);

        // Get all the fileSystemList
        restFileSystemMockMvc.perform(get("/api/file-systems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }
    
    @Test
    @Transactional
    public void getFileSystem() throws Exception {
        // Initialize the database
        fileSystemRepository.saveAndFlush(fileSystem);

        // Get the fileSystem
        restFileSystemMockMvc.perform(get("/api/file-systems/{id}", fileSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fileSystem.getId().intValue()))
            .andExpect(jsonPath("$.dataContentType").value(DEFAULT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.data").value(Base64Utils.encodeToString(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingFileSystem() throws Exception {
        // Get the fileSystem
        restFileSystemMockMvc.perform(get("/api/file-systems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileSystem() throws Exception {
        // Initialize the database
        fileSystemRepository.saveAndFlush(fileSystem);

        int databaseSizeBeforeUpdate = fileSystemRepository.findAll().size();

        // Update the fileSystem
        FileSystem updatedFileSystem = fileSystemRepository.findById(fileSystem.getId()).get();
        // Disconnect from session so that the updates on updatedFileSystem are not directly saved in db
        em.detach(updatedFileSystem);
        updatedFileSystem
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        FileSystemDTO fileSystemDTO = fileSystemMapper.toDto(updatedFileSystem);

        restFileSystemMockMvc.perform(put("/api/file-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileSystemDTO)))
            .andExpect(status().isOk());

        // Validate the FileSystem in the database
        List<FileSystem> fileSystemList = fileSystemRepository.findAll();
        assertThat(fileSystemList).hasSize(databaseSizeBeforeUpdate);
        FileSystem testFileSystem = fileSystemList.get(fileSystemList.size() - 1);
        assertThat(testFileSystem.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testFileSystem.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);

        // Validate the FileSystem in Elasticsearch
        verify(mockFileSystemSearchRepository, times(1)).save(testFileSystem);
    }

    @Test
    @Transactional
    public void updateNonExistingFileSystem() throws Exception {
        int databaseSizeBeforeUpdate = fileSystemRepository.findAll().size();

        // Create the FileSystem
        FileSystemDTO fileSystemDTO = fileSystemMapper.toDto(fileSystem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileSystemMockMvc.perform(put("/api/file-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileSystemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileSystem in the database
        List<FileSystem> fileSystemList = fileSystemRepository.findAll();
        assertThat(fileSystemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FileSystem in Elasticsearch
        verify(mockFileSystemSearchRepository, times(0)).save(fileSystem);
    }

    @Test
    @Transactional
    public void deleteFileSystem() throws Exception {
        // Initialize the database
        fileSystemRepository.saveAndFlush(fileSystem);

        int databaseSizeBeforeDelete = fileSystemRepository.findAll().size();

        // Delete the fileSystem
        restFileSystemMockMvc.perform(delete("/api/file-systems/{id}", fileSystem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileSystem> fileSystemList = fileSystemRepository.findAll();
        assertThat(fileSystemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FileSystem in Elasticsearch
        verify(mockFileSystemSearchRepository, times(1)).deleteById(fileSystem.getId());
    }

    @Test
    @Transactional
    public void searchFileSystem() throws Exception {
        // Initialize the database
        fileSystemRepository.saveAndFlush(fileSystem);
        when(mockFileSystemSearchRepository.search(queryStringQuery("id:" + fileSystem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(fileSystem), PageRequest.of(0, 1), 1));
        // Search the fileSystem
        restFileSystemMockMvc.perform(get("/api/_search/file-systems?query=id:" + fileSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }
}
