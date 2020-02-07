package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.Driver;
import com.jio.tms.v1.repository.DriverRepository;
import com.jio.tms.v1.repository.search.DriverSearchRepository;
import com.jio.tms.v1.service.DriverService;
import com.jio.tms.v1.service.dto.DriverDTO;
import com.jio.tms.v1.service.mapper.DriverMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.jio.tms.v1.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jio.tms.v1.domain.enumeration.ToggleStatus;
/**
 * Integration tests for the {@link DriverResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class DriverResourceIT {

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE_NUMBER = 1L;
    private static final Long UPDATED_PHONE_NUMBER = 2L;

    private static final Long DEFAULT_LICENCE_NUMBER = 1L;
    private static final Long UPDATED_LICENCE_NUMBER = 2L;

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_COMPANY_JOINED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPANY_JOINED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_COMPANY_LEFT_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPANY_LEFT_ON = LocalDate.now(ZoneId.systemDefault());

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_LICENCE_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LICENCE_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LICENCE_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LICENCE_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CONTRACT_DOC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTRACT_DOC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTRACT_DOC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTRACT_DOC_CONTENT_TYPE = "image/png";

    private static final ToggleStatus DEFAULT_STATUS = ToggleStatus.ACTIVE;
    private static final ToggleStatus UPDATED_STATUS = ToggleStatus.INACTIVE;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private DriverService driverService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.DriverSearchRepositoryMockConfiguration
     */
    @Autowired
    private DriverSearchRepository mockDriverSearchRepository;

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

    private MockMvc restDriverMockMvc;

    private Driver driver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DriverResource driverResource = new DriverResource(driverService);
        this.restDriverMockMvc = MockMvcBuilders.standaloneSetup(driverResource)
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
    public static Driver createEntity(EntityManager em) {
        Driver driver = new Driver()
            .company(DEFAULT_COMPANY)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .licenceNumber(DEFAULT_LICENCE_NUMBER)
            .dob(DEFAULT_DOB)
            .companyJoinedOn(DEFAULT_COMPANY_JOINED_ON)
            .companyLeftOn(DEFAULT_COMPANY_LEFT_ON)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .licenceImage(DEFAULT_LICENCE_IMAGE)
            .licenceImageContentType(DEFAULT_LICENCE_IMAGE_CONTENT_TYPE)
            .remarks(DEFAULT_REMARKS)
            .contractDoc(DEFAULT_CONTRACT_DOC)
            .contractDocContentType(DEFAULT_CONTRACT_DOC_CONTENT_TYPE)
            .status(DEFAULT_STATUS);
        return driver;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Driver createUpdatedEntity(EntityManager em) {
        Driver driver = new Driver()
            .company(UPDATED_COMPANY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .dob(UPDATED_DOB)
            .companyJoinedOn(UPDATED_COMPANY_JOINED_ON)
            .companyLeftOn(UPDATED_COMPANY_LEFT_ON)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .licenceImage(UPDATED_LICENCE_IMAGE)
            .licenceImageContentType(UPDATED_LICENCE_IMAGE_CONTENT_TYPE)
            .remarks(UPDATED_REMARKS)
            .contractDoc(UPDATED_CONTRACT_DOC)
            .contractDocContentType(UPDATED_CONTRACT_DOC_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        return driver;
    }

    @BeforeEach
    public void initTest() {
        driver = createEntity(em);
    }

    @Test
    @Transactional
    public void createDriver() throws Exception {
        int databaseSizeBeforeCreate = driverRepository.findAll().size();

        // Create the Driver
        DriverDTO driverDTO = driverMapper.toDto(driver);
        restDriverMockMvc.perform(post("/api/drivers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(driverDTO)))
            .andExpect(status().isCreated());

        // Validate the Driver in the database
        List<Driver> driverList = driverRepository.findAll();
        assertThat(driverList).hasSize(databaseSizeBeforeCreate + 1);
        Driver testDriver = driverList.get(driverList.size() - 1);
        assertThat(testDriver.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testDriver.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testDriver.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testDriver.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDriver.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDriver.getLicenceNumber()).isEqualTo(DEFAULT_LICENCE_NUMBER);
        assertThat(testDriver.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testDriver.getCompanyJoinedOn()).isEqualTo(DEFAULT_COMPANY_JOINED_ON);
        assertThat(testDriver.getCompanyLeftOn()).isEqualTo(DEFAULT_COMPANY_LEFT_ON);
        assertThat(testDriver.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testDriver.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testDriver.getLicenceImage()).isEqualTo(DEFAULT_LICENCE_IMAGE);
        assertThat(testDriver.getLicenceImageContentType()).isEqualTo(DEFAULT_LICENCE_IMAGE_CONTENT_TYPE);
        assertThat(testDriver.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testDriver.getContractDoc()).isEqualTo(DEFAULT_CONTRACT_DOC);
        assertThat(testDriver.getContractDocContentType()).isEqualTo(DEFAULT_CONTRACT_DOC_CONTENT_TYPE);
        assertThat(testDriver.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the Driver in Elasticsearch
        verify(mockDriverSearchRepository, times(1)).save(testDriver);
    }

    @Test
    @Transactional
    public void createDriverWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = driverRepository.findAll().size();

        // Create the Driver with an existing ID
        driver.setId(1L);
        DriverDTO driverDTO = driverMapper.toDto(driver);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDriverMockMvc.perform(post("/api/drivers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(driverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Driver in the database
        List<Driver> driverList = driverRepository.findAll();
        assertThat(driverList).hasSize(databaseSizeBeforeCreate);

        // Validate the Driver in Elasticsearch
        verify(mockDriverSearchRepository, times(0)).save(driver);
    }


    @Test
    @Transactional
    public void getAllDrivers() throws Exception {
        // Initialize the database
        driverRepository.saveAndFlush(driver);

        // Get all the driverList
        restDriverMockMvc.perform(get("/api/drivers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(driver.getId().intValue())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].licenceNumber").value(hasItem(DEFAULT_LICENCE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].companyJoinedOn").value(hasItem(DEFAULT_COMPANY_JOINED_ON.toString())))
            .andExpect(jsonPath("$.[*].companyLeftOn").value(hasItem(DEFAULT_COMPANY_LEFT_ON.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].licenceImageContentType").value(hasItem(DEFAULT_LICENCE_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].licenceImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_LICENCE_IMAGE))))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contractDocContentType").value(hasItem(DEFAULT_CONTRACT_DOC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contractDoc").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTRACT_DOC))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getDriver() throws Exception {
        // Initialize the database
        driverRepository.saveAndFlush(driver);

        // Get the driver
        restDriverMockMvc.perform(get("/api/drivers/{id}", driver.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(driver.getId().intValue()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.intValue()))
            .andExpect(jsonPath("$.licenceNumber").value(DEFAULT_LICENCE_NUMBER.intValue()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.companyJoinedOn").value(DEFAULT_COMPANY_JOINED_ON.toString()))
            .andExpect(jsonPath("$.companyLeftOn").value(DEFAULT_COMPANY_LEFT_ON.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.licenceImageContentType").value(DEFAULT_LICENCE_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.licenceImage").value(Base64Utils.encodeToString(DEFAULT_LICENCE_IMAGE)))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.contractDocContentType").value(DEFAULT_CONTRACT_DOC_CONTENT_TYPE))
            .andExpect(jsonPath("$.contractDoc").value(Base64Utils.encodeToString(DEFAULT_CONTRACT_DOC)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDriver() throws Exception {
        // Get the driver
        restDriverMockMvc.perform(get("/api/drivers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDriver() throws Exception {
        // Initialize the database
        driverRepository.saveAndFlush(driver);

        int databaseSizeBeforeUpdate = driverRepository.findAll().size();

        // Update the driver
        Driver updatedDriver = driverRepository.findById(driver.getId()).get();
        // Disconnect from session so that the updates on updatedDriver are not directly saved in db
        em.detach(updatedDriver);
        updatedDriver
            .company(UPDATED_COMPANY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .dob(UPDATED_DOB)
            .companyJoinedOn(UPDATED_COMPANY_JOINED_ON)
            .companyLeftOn(UPDATED_COMPANY_LEFT_ON)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .licenceImage(UPDATED_LICENCE_IMAGE)
            .licenceImageContentType(UPDATED_LICENCE_IMAGE_CONTENT_TYPE)
            .remarks(UPDATED_REMARKS)
            .contractDoc(UPDATED_CONTRACT_DOC)
            .contractDocContentType(UPDATED_CONTRACT_DOC_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        DriverDTO driverDTO = driverMapper.toDto(updatedDriver);

        restDriverMockMvc.perform(put("/api/drivers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(driverDTO)))
            .andExpect(status().isOk());

        // Validate the Driver in the database
        List<Driver> driverList = driverRepository.findAll();
        assertThat(driverList).hasSize(databaseSizeBeforeUpdate);
        Driver testDriver = driverList.get(driverList.size() - 1);
        assertThat(testDriver.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testDriver.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testDriver.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testDriver.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDriver.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDriver.getLicenceNumber()).isEqualTo(UPDATED_LICENCE_NUMBER);
        assertThat(testDriver.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testDriver.getCompanyJoinedOn()).isEqualTo(UPDATED_COMPANY_JOINED_ON);
        assertThat(testDriver.getCompanyLeftOn()).isEqualTo(UPDATED_COMPANY_LEFT_ON);
        assertThat(testDriver.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testDriver.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testDriver.getLicenceImage()).isEqualTo(UPDATED_LICENCE_IMAGE);
        assertThat(testDriver.getLicenceImageContentType()).isEqualTo(UPDATED_LICENCE_IMAGE_CONTENT_TYPE);
        assertThat(testDriver.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testDriver.getContractDoc()).isEqualTo(UPDATED_CONTRACT_DOC);
        assertThat(testDriver.getContractDocContentType()).isEqualTo(UPDATED_CONTRACT_DOC_CONTENT_TYPE);
        assertThat(testDriver.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the Driver in Elasticsearch
        verify(mockDriverSearchRepository, times(1)).save(testDriver);
    }

    @Test
    @Transactional
    public void updateNonExistingDriver() throws Exception {
        int databaseSizeBeforeUpdate = driverRepository.findAll().size();

        // Create the Driver
        DriverDTO driverDTO = driverMapper.toDto(driver);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDriverMockMvc.perform(put("/api/drivers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(driverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Driver in the database
        List<Driver> driverList = driverRepository.findAll();
        assertThat(driverList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Driver in Elasticsearch
        verify(mockDriverSearchRepository, times(0)).save(driver);
    }

    @Test
    @Transactional
    public void deleteDriver() throws Exception {
        // Initialize the database
        driverRepository.saveAndFlush(driver);

        int databaseSizeBeforeDelete = driverRepository.findAll().size();

        // Delete the driver
        restDriverMockMvc.perform(delete("/api/drivers/{id}", driver.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Driver> driverList = driverRepository.findAll();
        assertThat(driverList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Driver in Elasticsearch
        verify(mockDriverSearchRepository, times(1)).deleteById(driver.getId());
    }

    @Test
    @Transactional
    public void searchDriver() throws Exception {
        // Initialize the database
        driverRepository.saveAndFlush(driver);
        when(mockDriverSearchRepository.search(queryStringQuery("id:" + driver.getId())))
            .thenReturn(Collections.singletonList(driver));
        // Search the driver
        restDriverMockMvc.perform(get("/api/_search/drivers?query=id:" + driver.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(driver.getId().intValue())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].licenceNumber").value(hasItem(DEFAULT_LICENCE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].companyJoinedOn").value(hasItem(DEFAULT_COMPANY_JOINED_ON.toString())))
            .andExpect(jsonPath("$.[*].companyLeftOn").value(hasItem(DEFAULT_COMPANY_LEFT_ON.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].licenceImageContentType").value(hasItem(DEFAULT_LICENCE_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].licenceImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_LICENCE_IMAGE))))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contractDocContentType").value(hasItem(DEFAULT_CONTRACT_DOC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contractDoc").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTRACT_DOC))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
}
