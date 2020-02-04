package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OwnerOperatorMapperTest {

    private OwnerOperatorMapper ownerOperatorMapper;

    @BeforeEach
    public void setUp() {
        ownerOperatorMapper = new OwnerOperatorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(ownerOperatorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ownerOperatorMapper.fromId(null)).isNull();
    }
}
