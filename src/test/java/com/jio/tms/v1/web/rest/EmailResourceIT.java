package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.Email;
import com.jio.tms.v1.repository.EmailRepository;
import com.jio.tms.v1.repository.search.EmailSearchRepository;
import com.jio.tms.v1.service.EmailService;
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
 * Integration tests for the {@link EmailResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class EmailResourceIT {

    private static final String DEFAULT_USERTO = "AAAAAAAAAA";
    private static final String UPDATED_USERTO = "BBBBBBBBBB";

    private static final String DEFAULT_USERCC = "AAAAAAAAAA";
    private static final String UPDATED_USERCC = "BBBBBBBBBB";

    private static final String DEFAULT_USERBCC = "AAAAAAAAAA";
    private static final String UPDATED_USERBCC = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailService emailService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.EmailSearchRepositoryMockConfiguration
     */
    @Autowired
    private EmailSearchRepository mockEmailSearchRepository;

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

    private MockMvc restEmailMockMvc;

    private Email email;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmailResource emailResource = new EmailResource(emailService);
        this.restEmailMockMvc = MockMvcBuilders.standaloneSetup(emailResource)
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
    public static Email createEntity(EntityManager em) {
        Email email = new Email()
            .userto(DEFAULT_USERTO)
            .usercc(DEFAULT_USERCC)
            .userbcc(DEFAULT_USERBCC)
            .message(DEFAULT_MESSAGE);
        return email;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Email createUpdatedEntity(EntityManager em) {
        Email email = new Email()
            .userto(UPDATED_USERTO)
            .usercc(UPDATED_USERCC)
            .userbcc(UPDATED_USERBCC)
            .message(UPDATED_MESSAGE);
        return email;
    }

    @BeforeEach
    public void initTest() {
        email = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmail() throws Exception {
        int databaseSizeBeforeCreate = emailRepository.findAll().size();

        // Create the Email
        restEmailMockMvc.perform(post("/api/emails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(email)))
            .andExpect(status().isCreated());

        // Validate the Email in the database
        List<Email> emailList = emailRepository.findAll();
        assertThat(emailList).hasSize(databaseSizeBeforeCreate + 1);
        Email testEmail = emailList.get(emailList.size() - 1);
        assertThat(testEmail.getUserto()).isEqualTo(DEFAULT_USERTO);
        assertThat(testEmail.getUsercc()).isEqualTo(DEFAULT_USERCC);
        assertThat(testEmail.getUserbcc()).isEqualTo(DEFAULT_USERBCC);
        assertThat(testEmail.getMessage()).isEqualTo(DEFAULT_MESSAGE);

        // Validate the Email in Elasticsearch
        verify(mockEmailSearchRepository, times(1)).save(testEmail);
    }

    @Test
    @Transactional
    public void createEmailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailRepository.findAll().size();

        // Create the Email with an existing ID
        email.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailMockMvc.perform(post("/api/emails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(email)))
            .andExpect(status().isBadRequest());

        // Validate the Email in the database
        List<Email> emailList = emailRepository.findAll();
        assertThat(emailList).hasSize(databaseSizeBeforeCreate);

        // Validate the Email in Elasticsearch
        verify(mockEmailSearchRepository, times(0)).save(email);
    }


    @Test
    @Transactional
    public void getAllEmails() throws Exception {
        // Initialize the database
        emailRepository.saveAndFlush(email);

        // Get all the emailList
        restEmailMockMvc.perform(get("/api/emails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(email.getId().intValue())))
            .andExpect(jsonPath("$.[*].userto").value(hasItem(DEFAULT_USERTO)))
            .andExpect(jsonPath("$.[*].usercc").value(hasItem(DEFAULT_USERCC)))
            .andExpect(jsonPath("$.[*].userbcc").value(hasItem(DEFAULT_USERBCC)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
    
    @Test
    @Transactional
    public void getEmail() throws Exception {
        // Initialize the database
        emailRepository.saveAndFlush(email);

        // Get the email
        restEmailMockMvc.perform(get("/api/emails/{id}", email.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(email.getId().intValue()))
            .andExpect(jsonPath("$.userto").value(DEFAULT_USERTO))
            .andExpect(jsonPath("$.usercc").value(DEFAULT_USERCC))
            .andExpect(jsonPath("$.userbcc").value(DEFAULT_USERBCC))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
    }

    @Test
    @Transactional
    public void getNonExistingEmail() throws Exception {
        // Get the email
        restEmailMockMvc.perform(get("/api/emails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmail() throws Exception {
        // Initialize the database
        emailService.save(email);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockEmailSearchRepository);

        int databaseSizeBeforeUpdate = emailRepository.findAll().size();

        // Update the email
        Email updatedEmail = emailRepository.findById(email.getId()).get();
        // Disconnect from session so that the updates on updatedEmail are not directly saved in db
        em.detach(updatedEmail);
        updatedEmail
            .userto(UPDATED_USERTO)
            .usercc(UPDATED_USERCC)
            .userbcc(UPDATED_USERBCC)
            .message(UPDATED_MESSAGE);

        restEmailMockMvc.perform(put("/api/emails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmail)))
            .andExpect(status().isOk());

        // Validate the Email in the database
        List<Email> emailList = emailRepository.findAll();
        assertThat(emailList).hasSize(databaseSizeBeforeUpdate);
        Email testEmail = emailList.get(emailList.size() - 1);
        assertThat(testEmail.getUserto()).isEqualTo(UPDATED_USERTO);
        assertThat(testEmail.getUsercc()).isEqualTo(UPDATED_USERCC);
        assertThat(testEmail.getUserbcc()).isEqualTo(UPDATED_USERBCC);
        assertThat(testEmail.getMessage()).isEqualTo(UPDATED_MESSAGE);

        // Validate the Email in Elasticsearch
        verify(mockEmailSearchRepository, times(1)).save(testEmail);
    }

    @Test
    @Transactional
    public void updateNonExistingEmail() throws Exception {
        int databaseSizeBeforeUpdate = emailRepository.findAll().size();

        // Create the Email

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmailMockMvc.perform(put("/api/emails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(email)))
            .andExpect(status().isBadRequest());

        // Validate the Email in the database
        List<Email> emailList = emailRepository.findAll();
        assertThat(emailList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Email in Elasticsearch
        verify(mockEmailSearchRepository, times(0)).save(email);
    }

    @Test
    @Transactional
    public void deleteEmail() throws Exception {
        // Initialize the database
        emailService.save(email);

        int databaseSizeBeforeDelete = emailRepository.findAll().size();

        // Delete the email
        restEmailMockMvc.perform(delete("/api/emails/{id}", email.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Email> emailList = emailRepository.findAll();
        assertThat(emailList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Email in Elasticsearch
        verify(mockEmailSearchRepository, times(1)).deleteById(email.getId());
    }

    @Test
    @Transactional
    public void searchEmail() throws Exception {
        // Initialize the database
        emailService.save(email);
        when(mockEmailSearchRepository.search(queryStringQuery("id:" + email.getId())))
            .thenReturn(Collections.singletonList(email));
        // Search the email
        restEmailMockMvc.perform(get("/api/_search/emails?query=id:" + email.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(email.getId().intValue())))
            .andExpect(jsonPath("$.[*].userto").value(hasItem(DEFAULT_USERTO)))
            .andExpect(jsonPath("$.[*].usercc").value(hasItem(DEFAULT_USERCC)))
            .andExpect(jsonPath("$.[*].userbcc").value(hasItem(DEFAULT_USERBCC)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
}
