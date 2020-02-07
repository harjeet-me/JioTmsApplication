package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerMapperTest {

    private ContainerMapper containerMapper;

    @BeforeEach
    public void setUp() {
        containerMapper = new ContainerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(containerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(containerMapper.fromId(null)).isNull();
    }
}
