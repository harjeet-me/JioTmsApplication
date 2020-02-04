package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InvoiceItemMapperTest {

    private InvoiceItemMapper invoiceItemMapper;

    @BeforeEach
    public void setUp() {
        invoiceItemMapper = new InvoiceItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(invoiceItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invoiceItemMapper.fromId(null)).isNull();
    }
}
