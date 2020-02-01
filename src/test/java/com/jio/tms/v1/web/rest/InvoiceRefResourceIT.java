package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.InvoiceRef;
import com.jio.tms.v1.repository.InvoiceRefRepository;
import com.jio.tms.v1.repository.search.InvoiceRefSearchRepository;
import com.jio.tms.v1.service.InvoiceRefService;
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
 * Integration tests for the {@link InvoiceRefResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class InvoiceRefResourceIT {

    private static final String DEFAULT_REF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REF_NAME = "BBBBBBBBBB";

    @Autowired
    private InvoiceRefRepository invoiceRefRepository;

    @Autowired
    private InvoiceRefService invoiceRefService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.InvoiceRefSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvoiceRefSearchRepository mockInvoiceRefSearchRepository;

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

    private MockMvc restInvoiceRefMockMvc;

    private InvoiceRef invoiceRef;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvoiceRefResource invoiceRefResource = new InvoiceRefResource(invoiceRefService);
        this.restInvoiceRefMockMvc = MockMvcBuilders.standaloneSetup(invoiceRefResource)
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
    public static InvoiceRef createEntity(EntityManager em) {
        InvoiceRef invoiceRef = new InvoiceRef()
            .refName(DEFAULT_REF_NAME);
        return invoiceRef;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceRef createUpdatedEntity(EntityManager em) {
        InvoiceRef invoiceRef = new InvoiceRef()
            .refName(UPDATED_REF_NAME);
        return invoiceRef;
    }

    @BeforeEach
    public void initTest() {
        invoiceRef = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceRef() throws Exception {
        int databaseSizeBeforeCreate = invoiceRefRepository.findAll().size();

        // Create the InvoiceRef
        restInvoiceRefMockMvc.perform(post("/api/invoice-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceRef)))
            .andExpect(status().isCreated());

        // Validate the InvoiceRef in the database
        List<InvoiceRef> invoiceRefList = invoiceRefRepository.findAll();
        assertThat(invoiceRefList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceRef testInvoiceRef = invoiceRefList.get(invoiceRefList.size() - 1);
        assertThat(testInvoiceRef.getRefName()).isEqualTo(DEFAULT_REF_NAME);

        // Validate the InvoiceRef in Elasticsearch
        verify(mockInvoiceRefSearchRepository, times(1)).save(testInvoiceRef);
    }

    @Test
    @Transactional
    public void createInvoiceRefWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceRefRepository.findAll().size();

        // Create the InvoiceRef with an existing ID
        invoiceRef.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceRefMockMvc.perform(post("/api/invoice-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceRef)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceRef in the database
        List<InvoiceRef> invoiceRefList = invoiceRefRepository.findAll();
        assertThat(invoiceRefList).hasSize(databaseSizeBeforeCreate);

        // Validate the InvoiceRef in Elasticsearch
        verify(mockInvoiceRefSearchRepository, times(0)).save(invoiceRef);
    }


    @Test
    @Transactional
    public void getAllInvoiceRefs() throws Exception {
        // Initialize the database
        invoiceRefRepository.saveAndFlush(invoiceRef);

        // Get all the invoiceRefList
        restInvoiceRefMockMvc.perform(get("/api/invoice-refs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceRef.getId().intValue())))
            .andExpect(jsonPath("$.[*].refName").value(hasItem(DEFAULT_REF_NAME)));
    }
    
    @Test
    @Transactional
    public void getInvoiceRef() throws Exception {
        // Initialize the database
        invoiceRefRepository.saveAndFlush(invoiceRef);

        // Get the invoiceRef
        restInvoiceRefMockMvc.perform(get("/api/invoice-refs/{id}", invoiceRef.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceRef.getId().intValue()))
            .andExpect(jsonPath("$.refName").value(DEFAULT_REF_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInvoiceRef() throws Exception {
        // Get the invoiceRef
        restInvoiceRefMockMvc.perform(get("/api/invoice-refs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceRef() throws Exception {
        // Initialize the database
        invoiceRefService.save(invoiceRef);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockInvoiceRefSearchRepository);

        int databaseSizeBeforeUpdate = invoiceRefRepository.findAll().size();

        // Update the invoiceRef
        InvoiceRef updatedInvoiceRef = invoiceRefRepository.findById(invoiceRef.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceRef are not directly saved in db
        em.detach(updatedInvoiceRef);
        updatedInvoiceRef
            .refName(UPDATED_REF_NAME);

        restInvoiceRefMockMvc.perform(put("/api/invoice-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceRef)))
            .andExpect(status().isOk());

        // Validate the InvoiceRef in the database
        List<InvoiceRef> invoiceRefList = invoiceRefRepository.findAll();
        assertThat(invoiceRefList).hasSize(databaseSizeBeforeUpdate);
        InvoiceRef testInvoiceRef = invoiceRefList.get(invoiceRefList.size() - 1);
        assertThat(testInvoiceRef.getRefName()).isEqualTo(UPDATED_REF_NAME);

        // Validate the InvoiceRef in Elasticsearch
        verify(mockInvoiceRefSearchRepository, times(1)).save(testInvoiceRef);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceRef() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRefRepository.findAll().size();

        // Create the InvoiceRef

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceRefMockMvc.perform(put("/api/invoice-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invoiceRef)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceRef in the database
        List<InvoiceRef> invoiceRefList = invoiceRefRepository.findAll();
        assertThat(invoiceRefList).hasSize(databaseSizeBeforeUpdate);

        // Validate the InvoiceRef in Elasticsearch
        verify(mockInvoiceRefSearchRepository, times(0)).save(invoiceRef);
    }

    @Test
    @Transactional
    public void deleteInvoiceRef() throws Exception {
        // Initialize the database
        invoiceRefService.save(invoiceRef);

        int databaseSizeBeforeDelete = invoiceRefRepository.findAll().size();

        // Delete the invoiceRef
        restInvoiceRefMockMvc.perform(delete("/api/invoice-refs/{id}", invoiceRef.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceRef> invoiceRefList = invoiceRefRepository.findAll();
        assertThat(invoiceRefList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the InvoiceRef in Elasticsearch
        verify(mockInvoiceRefSearchRepository, times(1)).deleteById(invoiceRef.getId());
    }

    @Test
    @Transactional
    public void searchInvoiceRef() throws Exception {
        // Initialize the database
        invoiceRefService.save(invoiceRef);
        when(mockInvoiceRefSearchRepository.search(queryStringQuery("id:" + invoiceRef.getId())))
            .thenReturn(Collections.singletonList(invoiceRef));
        // Search the invoiceRef
        restInvoiceRefMockMvc.perform(get("/api/_search/invoice-refs?query=id:" + invoiceRef.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceRef.getId().intValue())))
            .andExpect(jsonPath("$.[*].refName").value(hasItem(DEFAULT_REF_NAME)));
    }
}
