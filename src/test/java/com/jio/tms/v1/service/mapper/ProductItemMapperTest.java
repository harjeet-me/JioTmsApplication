package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductItemMapperTest {

    private ProductItemMapper productItemMapper;

    @BeforeEach
    public void setUp() {
        productItemMapper = new ProductItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productItemMapper.fromId(null)).isNull();
    }
}
