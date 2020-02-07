package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.Container;
import com.jio.tms.v1.repository.ContainerRepository;
import com.jio.tms.v1.repository.search.ContainerSearchRepository;
import com.jio.tms.v1.service.ContainerService;
import com.jio.tms.v1.service.dto.ContainerDTO;
import com.jio.tms.v1.service.mapper.ContainerMapper;
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
 * Integration tests for the {@link ContainerResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class ContainerResourceIT {

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private ContainerMapper containerMapper;

    @Autowired
    private ContainerService containerService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.ContainerSearchRepositoryMockConfiguration
     */
    @Autowired
    private ContainerSearchRepository mockContainerSearchRepository;

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

    private MockMvc restContainerMockMvc;

    private Container container;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContainerResource containerResource = new ContainerResource(containerService);
        this.restContainerMockMvc = MockMvcBuilders.standaloneSetup(containerResource)
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
    public static Container createEntity(EntityManager em) {
        Container container = new Container()
            .number(DEFAULT_NUMBER)
            .description(DEFAULT_DESCRIPTION)
            .size(DEFAULT_SIZE);
        return container;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Container createUpdatedEntity(EntityManager em) {
        Container container = new Container()
            .number(UPDATED_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .size(UPDATED_SIZE);
        return container;
    }

    @BeforeEach
    public void initTest() {
        container = createEntity(em);
    }

    @Test
    @Transactional
    public void createContainer() throws Exception {
        int databaseSizeBeforeCreate = containerRepository.findAll().size();

        // Create the Container
        ContainerDTO containerDTO = containerMapper.toDto(container);
        restContainerMockMvc.perform(post("/api/containers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(containerDTO)))
            .andExpect(status().isCreated());

        // Validate the Container in the database
        List<Container> containerList = containerRepository.findAll();
        assertThat(containerList).hasSize(databaseSizeBeforeCreate + 1);
        Container testContainer = containerList.get(containerList.size() - 1);
        assertThat(testContainer.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testContainer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testContainer.getSize()).isEqualTo(DEFAULT_SIZE);

        // Validate the Container in Elasticsearch
        verify(mockContainerSearchRepository, times(1)).save(testContainer);
    }

    @Test
    @Transactional
    public void createContainerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = containerRepository.findAll().size();

        // Create the Container with an existing ID
        container.setId(1L);
        ContainerDTO containerDTO = containerMapper.toDto(container);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContainerMockMvc.perform(post("/api/containers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(containerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Container in the database
        List<Container> containerList = containerRepository.findAll();
        assertThat(containerList).hasSize(databaseSizeBeforeCreate);

        // Validate the Container in Elasticsearch
        verify(mockContainerSearchRepository, times(0)).save(container);
    }


    @Test
    @Transactional
    public void getAllContainers() throws Exception {
        // Initialize the database
        containerRepository.saveAndFlush(container);

        // Get all the containerList
        restContainerMockMvc.perform(get("/api/containers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(container.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)));
    }
    
    @Test
    @Transactional
    public void getContainer() throws Exception {
        // Initialize the database
        containerRepository.saveAndFlush(container);

        // Get the container
        restContainerMockMvc.perform(get("/api/containers/{id}", container.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(container.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE));
    }

    @Test
    @Transactional
    public void getNonExistingContainer() throws Exception {
        // Get the container
        restContainerMockMvc.perform(get("/api/containers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContainer() throws Exception {
        // Initialize the database
        containerRepository.saveAndFlush(container);

        int databaseSizeBeforeUpdate = containerRepository.findAll().size();

        // Update the container
        Container updatedContainer = containerRepository.findById(container.getId()).get();
        // Disconnect from session so that the updates on updatedContainer are not directly saved in db
        em.detach(updatedContainer);
        updatedContainer
            .number(UPDATED_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .size(UPDATED_SIZE);
        ContainerDTO containerDTO = containerMapper.toDto(updatedContainer);

        restContainerMockMvc.perform(put("/api/containers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(containerDTO)))
            .andExpect(status().isOk());

        // Validate the Container in the database
        List<Container> containerList = containerRepository.findAll();
        assertThat(containerList).hasSize(databaseSizeBeforeUpdate);
        Container testContainer = containerList.get(containerList.size() - 1);
        assertThat(testContainer.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testContainer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testContainer.getSize()).isEqualTo(UPDATED_SIZE);

        // Validate the Container in Elasticsearch
        verify(mockContainerSearchRepository, times(1)).save(testContainer);
    }

    @Test
    @Transactional
    public void updateNonExistingContainer() throws Exception {
        int databaseSizeBeforeUpdate = containerRepository.findAll().size();

        // Create the Container
        ContainerDTO containerDTO = containerMapper.toDto(container);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContainerMockMvc.perform(put("/api/containers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(containerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Container in the database
        List<Container> containerList = containerRepository.findAll();
        assertThat(containerList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Container in Elasticsearch
        verify(mockContainerSearchRepository, times(0)).save(container);
    }

    @Test
    @Transactional
    public void deleteContainer() throws Exception {
        // Initialize the database
        containerRepository.saveAndFlush(container);

        int databaseSizeBeforeDelete = containerRepository.findAll().size();

        // Delete the container
        restContainerMockMvc.perform(delete("/api/containers/{id}", container.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Container> containerList = containerRepository.findAll();
        assertThat(containerList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Container in Elasticsearch
        verify(mockContainerSearchRepository, times(1)).deleteById(container.getId());
    }

    @Test
    @Transactional
    public void searchContainer() throws Exception {
        // Initialize the database
        containerRepository.saveAndFlush(container);
        when(mockContainerSearchRepository.search(queryStringQuery("id:" + container.getId())))
            .thenReturn(Collections.singletonList(container));
        // Search the container
        restContainerMockMvc.perform(get("/api/_search/containers?query=id:" + container.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(container.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)));
    }
}
