package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.Reference;
import com.jio.tms.v1.repository.ReferenceRepository;
import com.jio.tms.v1.repository.search.ReferenceSearchRepository;
import com.jio.tms.v1.service.ReferenceService;
import com.jio.tms.v1.service.dto.ReferenceDTO;
import com.jio.tms.v1.service.mapper.ReferenceMapper;
import com.jio.tms.v1.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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
 * Integration tests for the {@link ReferenceResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class ReferenceResourceIT {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private ReferenceMapper referenceMapper;

    @Autowired
    private ReferenceService referenceService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.ReferenceSearchRepositoryMockConfiguration
     */
    @Autowired
    private ReferenceSearchRepository mockReferenceSearchRepository;

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

    private MockMvc restReferenceMockMvc;

    private Reference reference;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReferenceResource referenceResource = new ReferenceResource(referenceService);
        this.restReferenceMockMvc = MockMvcBuilders.standaloneSetup(referenceResource)
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
    public static Reference createEntity(EntityManager em) {
        Reference reference = new Reference()
            .reference(DEFAULT_REFERENCE);
        return reference;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reference createUpdatedEntity(EntityManager em) {
        Reference reference = new Reference()
            .reference(UPDATED_REFERENCE);
        return reference;
    }

    @BeforeEach
    public void initTest() {
        reference = createEntity(em);
    }

    @Test
    @Transactional
    public void createReference() throws Exception {
        int databaseSizeBeforeCreate = referenceRepository.findAll().size();

        // Create the Reference
        ReferenceDTO referenceDTO = referenceMapper.toDto(reference);
        restReferenceMockMvc.perform(post("/api/references")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Reference in the database
        List<Reference> referenceList = referenceRepository.findAll();
        assertThat(referenceList).hasSize(databaseSizeBeforeCreate + 1);
        Reference testReference = referenceList.get(referenceList.size() - 1);
        assertThat(testReference.getReference()).isEqualTo(DEFAULT_REFERENCE);

        // Validate the Reference in Elasticsearch
        verify(mockReferenceSearchRepository, times(1)).save(testReference);
    }

    @Test
    @Transactional
    public void createReferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referenceRepository.findAll().size();

        // Create the Reference with an existing ID
        reference.setId(1L);
        ReferenceDTO referenceDTO = referenceMapper.toDto(reference);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferenceMockMvc.perform(post("/api/references")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reference in the database
        List<Reference> referenceList = referenceRepository.findAll();
        assertThat(referenceList).hasSize(databaseSizeBeforeCreate);

        // Validate the Reference in Elasticsearch
        verify(mockReferenceSearchRepository, times(0)).save(reference);
    }


    @Test
    @Transactional
    public void getAllReferences() throws Exception {
        // Initialize the database
        referenceRepository.saveAndFlush(reference);

        // Get all the referenceList
        restReferenceMockMvc.perform(get("/api/references?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reference.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)));
    }
    
    @Test
    @Transactional
    public void getReference() throws Exception {
        // Initialize the database
        referenceRepository.saveAndFlush(reference);

        // Get the reference
        restReferenceMockMvc.perform(get("/api/references/{id}", reference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reference.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE));
    }

    @Test
    @Transactional
    public void getNonExistingReference() throws Exception {
        // Get the reference
        restReferenceMockMvc.perform(get("/api/references/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReference() throws Exception {
        // Initialize the database
        referenceRepository.saveAndFlush(reference);

        int databaseSizeBeforeUpdate = referenceRepository.findAll().size();

        // Update the reference
        Reference updatedReference = referenceRepository.findById(reference.getId()).get();
        // Disconnect from session so that the updates on updatedReference are not directly saved in db
        em.detach(updatedReference);
        updatedReference
            .reference(UPDATED_REFERENCE);
        ReferenceDTO referenceDTO = referenceMapper.toDto(updatedReference);

        restReferenceMockMvc.perform(put("/api/references")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referenceDTO)))
            .andExpect(status().isOk());

        // Validate the Reference in the database
        List<Reference> referenceList = referenceRepository.findAll();
        assertThat(referenceList).hasSize(databaseSizeBeforeUpdate);
        Reference testReference = referenceList.get(referenceList.size() - 1);
        assertThat(testReference.getReference()).isEqualTo(UPDATED_REFERENCE);

        // Validate the Reference in Elasticsearch
        verify(mockReferenceSearchRepository, times(1)).save(testReference);
    }

    @Test
    @Transactional
    public void updateNonExistingReference() throws Exception {
        int databaseSizeBeforeUpdate = referenceRepository.findAll().size();

        // Create the Reference
        ReferenceDTO referenceDTO = referenceMapper.toDto(reference);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferenceMockMvc.perform(put("/api/references")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reference in the database
        List<Reference> referenceList = referenceRepository.findAll();
        assertThat(referenceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Reference in Elasticsearch
        verify(mockReferenceSearchRepository, times(0)).save(reference);
    }

    @Test
    @Transactional
    public void deleteReference() throws Exception {
        // Initialize the database
        referenceRepository.saveAndFlush(reference);

        int databaseSizeBeforeDelete = referenceRepository.findAll().size();

        // Delete the reference
        restReferenceMockMvc.perform(delete("/api/references/{id}", reference.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reference> referenceList = referenceRepository.findAll();
        assertThat(referenceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Reference in Elasticsearch
        verify(mockReferenceSearchRepository, times(1)).deleteById(reference.getId());
    }

    @Test
    @Transactional
    public void searchReference() throws Exception {
        // Initialize the database
        referenceRepository.saveAndFlush(reference);
        when(mockReferenceSearchRepository.search(queryStringQuery("id:" + reference.getId())))
            .thenReturn(Collections.singletonList(reference));
        // Search the reference
        restReferenceMockMvc.perform(get("/api/_search/references?query=id:" + reference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reference.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)));
    }
}
