package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class CompanyProfileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyProfileDTO.class);
        CompanyProfileDTO companyProfileDTO1 = new CompanyProfileDTO();
        companyProfileDTO1.setId(1L);
        CompanyProfileDTO companyProfileDTO2 = new CompanyProfileDTO();
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
        companyProfileDTO2.setId(companyProfileDTO1.getId());
        assertThat(companyProfileDTO1).isEqualTo(companyProfileDTO2);
        companyProfileDTO2.setId(2L);
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
        companyProfileDTO1.setId(null);
        assertThat(companyProfileDTO1).isNotEqualTo(companyProfileDTO2);
    }
}
