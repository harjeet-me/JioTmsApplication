package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CompanyProfileMapperTest {

    private CompanyProfileMapper companyProfileMapper;

    @BeforeEach
    public void setUp() {
        companyProfileMapper = new CompanyProfileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(companyProfileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(companyProfileMapper.fromId(null)).isNull();
    }
}
