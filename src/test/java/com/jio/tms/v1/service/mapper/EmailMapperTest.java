package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EmailMapperTest {

    private EmailMapper emailMapper;

    @BeforeEach
    public void setUp() {
        emailMapper = new EmailMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(emailMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(emailMapper.fromId(null)).isNull();
    }
}