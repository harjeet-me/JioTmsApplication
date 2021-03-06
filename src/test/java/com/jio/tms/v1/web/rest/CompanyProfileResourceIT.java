package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.CompanyProfile;
import com.jio.tms.v1.repository.CompanyProfileRepository;
import com.jio.tms.v1.repository.search.CompanyProfileSearchRepository;
import com.jio.tms.v1.service.CompanyProfileService;
import com.jio.tms.v1.service.dto.CompanyProfileDTO;
import com.jio.tms.v1.service.mapper.CompanyProfileMapper;
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
import java.util.Collections;
import java.util.List;

import static com.jio.tms.v1.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jio.tms.v1.domain.enumeration.CountryEnum;
import com.jio.tms.v1.domain.enumeration.ToggleStatus;
import com.jio.tms.v1.domain.enumeration.CURRENCY;
/**
 * Integration tests for the {@link CompanyProfileResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class CompanyProfileResourceIT {

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_PROVINCE = "BBBBBBBBBB";

    private static final CountryEnum DEFAULT_COUNTRY = CountryEnum.USA;
    private static final CountryEnum UPDATED_COUNTRY = CountryEnum.CANADA;

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE_NUMBER = 1L;
    private static final Long UPDATED_PHONE_NUMBER = 2L;

    private static final String DEFAULT_DOT = "AAAAAAAAAA";
    private static final String UPDATED_DOT = "BBBBBBBBBB";

    private static final Long DEFAULT_MC = 1L;
    private static final Long UPDATED_MC = 2L;

    private static final byte[] DEFAULT_COMPANY_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COMPANY_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_COMPANY_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COMPANY_LOGO_CONTENT_TYPE = "image/png";

    private static final ToggleStatus DEFAULT_PROFILE_STATUS = ToggleStatus.ACTIVE;
    private static final ToggleStatus UPDATED_PROFILE_STATUS = ToggleStatus.INACTIVE;

    private static final CURRENCY DEFAULT_PREFFRED_CURRENCY = CURRENCY.USD;
    private static final CURRENCY UPDATED_PREFFRED_CURRENCY = CURRENCY.CAD;

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyProfileMapper companyProfileMapper;

    @Autowired
    private CompanyProfileService companyProfileService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.CompanyProfileSearchRepositoryMockConfiguration
     */
    @Autowired
    private CompanyProfileSearchRepository mockCompanyProfileSearchRepository;

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

    private MockMvc restCompanyProfileMockMvc;

    private CompanyProfile companyProfile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompanyProfileResource companyProfileResource = new CompanyProfileResource(companyProfileService);
        this.restCompanyProfileMockMvc = MockMvcBuilders.standaloneSetup(companyProfileResource)
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
    public static CompanyProfile createEntity(EntityManager em) {
        CompanyProfile companyProfile = new CompanyProfile()
            .active(DEFAULT_ACTIVE)
            .company(DEFAULT_COMPANY)
            .address(DEFAULT_ADDRESS)
            .streetAddress(DEFAULT_STREET_ADDRESS)
            .city(DEFAULT_CITY)
            .stateProvince(DEFAULT_STATE_PROVINCE)
            .country(DEFAULT_COUNTRY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .email(DEFAULT_EMAIL)
            .website(DEFAULT_WEBSITE)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .dot(DEFAULT_DOT)
            .mc(DEFAULT_MC)
            .companyLogo(DEFAULT_COMPANY_LOGO)
            .companyLogoContentType(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)
            .profileStatus(DEFAULT_PROFILE_STATUS)
            .preffredCurrency(DEFAULT_PREFFRED_CURRENCY);
        return companyProfile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyProfile createUpdatedEntity(EntityManager em) {
        CompanyProfile companyProfile = new CompanyProfile()
            .active(UPDATED_ACTIVE)
            .company(UPDATED_COMPANY)
            .address(UPDATED_ADDRESS)
            .streetAddress(UPDATED_STREET_ADDRESS)
            .city(UPDATED_CITY)
            .stateProvince(UPDATED_STATE_PROVINCE)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .email(UPDATED_EMAIL)
            .website(UPDATED_WEBSITE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .dot(UPDATED_DOT)
            .mc(UPDATED_MC)
            .companyLogo(UPDATED_COMPANY_LOGO)
            .companyLogoContentType(UPDATED_COMPANY_LOGO_CONTENT_TYPE)
            .profileStatus(UPDATED_PROFILE_STATUS)
            .preffredCurrency(UPDATED_PREFFRED_CURRENCY);
        return companyProfile;
    }

    @BeforeEach
    public void initTest() {
        companyProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyProfile() throws Exception {
        int databaseSizeBeforeCreate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);
        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyProfile testCompanyProfile = companyProfileList.get(companyProfileList.size() - 1);
        assertThat(testCompanyProfile.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testCompanyProfile.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testCompanyProfile.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCompanyProfile.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testCompanyProfile.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCompanyProfile.getStateProvince()).isEqualTo(DEFAULT_STATE_PROVINCE);
        assertThat(testCompanyProfile.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testCompanyProfile.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testCompanyProfile.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCompanyProfile.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testCompanyProfile.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testCompanyProfile.getDot()).isEqualTo(DEFAULT_DOT);
        assertThat(testCompanyProfile.getMc()).isEqualTo(DEFAULT_MC);
        assertThat(testCompanyProfile.getCompanyLogo()).isEqualTo(DEFAULT_COMPANY_LOGO);
        assertThat(testCompanyProfile.getCompanyLogoContentType()).isEqualTo(DEFAULT_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testCompanyProfile.getProfileStatus()).isEqualTo(DEFAULT_PROFILE_STATUS);
        assertThat(testCompanyProfile.getPreffredCurrency()).isEqualTo(DEFAULT_PREFFRED_CURRENCY);

        // Validate the CompanyProfile in Elasticsearch
        verify(mockCompanyProfileSearchRepository, times(1)).save(testCompanyProfile);
    }

    @Test
    @Transactional
    public void createCompanyProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile with an existing ID
        companyProfile.setId(1L);
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyProfileMockMvc.perform(post("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeCreate);

        // Validate the CompanyProfile in Elasticsearch
        verify(mockCompanyProfileSearchRepository, times(0)).save(companyProfile);
    }


    @Test
    @Transactional
    public void getAllCompanyProfiles() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        // Get all the companyProfileList
        restCompanyProfileMockMvc.perform(get("/api/company-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].stateProvince").value(hasItem(DEFAULT_STATE_PROVINCE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].dot").value(hasItem(DEFAULT_DOT)))
            .andExpect(jsonPath("$.[*].mc").value(hasItem(DEFAULT_MC.intValue())))
            .andExpect(jsonPath("$.[*].companyLogoContentType").value(hasItem(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].companyLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO))))
            .andExpect(jsonPath("$.[*].profileStatus").value(hasItem(DEFAULT_PROFILE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].preffredCurrency").value(hasItem(DEFAULT_PREFFRED_CURRENCY.toString())));
    }
    
    @Test
    @Transactional
    public void getCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        // Get the companyProfile
        restCompanyProfileMockMvc.perform(get("/api/company-profiles/{id}", companyProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(companyProfile.getId().intValue()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.streetAddress").value(DEFAULT_STREET_ADDRESS))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.stateProvince").value(DEFAULT_STATE_PROVINCE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.intValue()))
            .andExpect(jsonPath("$.dot").value(DEFAULT_DOT))
            .andExpect(jsonPath("$.mc").value(DEFAULT_MC.intValue()))
            .andExpect(jsonPath("$.companyLogoContentType").value(DEFAULT_COMPANY_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.companyLogo").value(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO)))
            .andExpect(jsonPath("$.profileStatus").value(DEFAULT_PROFILE_STATUS.toString()))
            .andExpect(jsonPath("$.preffredCurrency").value(DEFAULT_PREFFRED_CURRENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyProfile() throws Exception {
        // Get the companyProfile
        restCompanyProfileMockMvc.perform(get("/api/company-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        int databaseSizeBeforeUpdate = companyProfileRepository.findAll().size();

        // Update the companyProfile
        CompanyProfile updatedCompanyProfile = companyProfileRepository.findById(companyProfile.getId()).get();
        // Disconnect from session so that the updates on updatedCompanyProfile are not directly saved in db
        em.detach(updatedCompanyProfile);
        updatedCompanyProfile
            .active(UPDATED_ACTIVE)
            .company(UPDATED_COMPANY)
            .address(UPDATED_ADDRESS)
            .streetAddress(UPDATED_STREET_ADDRESS)
            .city(UPDATED_CITY)
            .stateProvince(UPDATED_STATE_PROVINCE)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .email(UPDATED_EMAIL)
            .website(UPDATED_WEBSITE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .dot(UPDATED_DOT)
            .mc(UPDATED_MC)
            .companyLogo(UPDATED_COMPANY_LOGO)
            .companyLogoContentType(UPDATED_COMPANY_LOGO_CONTENT_TYPE)
            .profileStatus(UPDATED_PROFILE_STATUS)
            .preffredCurrency(UPDATED_PREFFRED_CURRENCY);
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(updatedCompanyProfile);

        restCompanyProfileMockMvc.perform(put("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isOk());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeUpdate);
        CompanyProfile testCompanyProfile = companyProfileList.get(companyProfileList.size() - 1);
        assertThat(testCompanyProfile.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testCompanyProfile.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testCompanyProfile.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCompanyProfile.getStreetAddress()).isEqualTo(UPDATED_STREET_ADDRESS);
        assertThat(testCompanyProfile.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCompanyProfile.getStateProvince()).isEqualTo(UPDATED_STATE_PROVINCE);
        assertThat(testCompanyProfile.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testCompanyProfile.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testCompanyProfile.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCompanyProfile.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testCompanyProfile.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCompanyProfile.getDot()).isEqualTo(UPDATED_DOT);
        assertThat(testCompanyProfile.getMc()).isEqualTo(UPDATED_MC);
        assertThat(testCompanyProfile.getCompanyLogo()).isEqualTo(UPDATED_COMPANY_LOGO);
        assertThat(testCompanyProfile.getCompanyLogoContentType()).isEqualTo(UPDATED_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testCompanyProfile.getProfileStatus()).isEqualTo(UPDATED_PROFILE_STATUS);
        assertThat(testCompanyProfile.getPreffredCurrency()).isEqualTo(UPDATED_PREFFRED_CURRENCY);

        // Validate the CompanyProfile in Elasticsearch
        verify(mockCompanyProfileSearchRepository, times(1)).save(testCompanyProfile);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyProfile() throws Exception {
        int databaseSizeBeforeUpdate = companyProfileRepository.findAll().size();

        // Create the CompanyProfile
        CompanyProfileDTO companyProfileDTO = companyProfileMapper.toDto(companyProfile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyProfileMockMvc.perform(put("/api/company-profiles")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyProfile in the database
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CompanyProfile in Elasticsearch
        verify(mockCompanyProfileSearchRepository, times(0)).save(companyProfile);
    }

    @Test
    @Transactional
    public void deleteCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);

        int databaseSizeBeforeDelete = companyProfileRepository.findAll().size();

        // Delete the companyProfile
        restCompanyProfileMockMvc.perform(delete("/api/company-profiles/{id}", companyProfile.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompanyProfile> companyProfileList = companyProfileRepository.findAll();
        assertThat(companyProfileList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CompanyProfile in Elasticsearch
        verify(mockCompanyProfileSearchRepository, times(1)).deleteById(companyProfile.getId());
    }

    @Test
    @Transactional
    public void searchCompanyProfile() throws Exception {
        // Initialize the database
        companyProfileRepository.saveAndFlush(companyProfile);
        when(mockCompanyProfileSearchRepository.search(queryStringQuery("id:" + companyProfile.getId())))
            .thenReturn(Collections.singletonList(companyProfile));
        // Search the companyProfile
        restCompanyProfileMockMvc.perform(get("/api/_search/company-profiles?query=id:" + companyProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].stateProvince").value(hasItem(DEFAULT_STATE_PROVINCE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].dot").value(hasItem(DEFAULT_DOT)))
            .andExpect(jsonPath("$.[*].mc").value(hasItem(DEFAULT_MC.intValue())))
            .andExpect(jsonPath("$.[*].companyLogoContentType").value(hasItem(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].companyLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO))))
            .andExpect(jsonPath("$.[*].profileStatus").value(hasItem(DEFAULT_PROFILE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].preffredCurrency").value(hasItem(DEFAULT_PREFFRED_CURRENCY.toString())));
    }
}
