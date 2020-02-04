package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InvoiceMapperTest {

    private InvoiceMapper invoiceMapper;

    @BeforeEach
    public void setUp() {
        invoiceMapper = new InvoiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(invoiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invoiceMapper.fromId(null)).isNull();
    }
}
