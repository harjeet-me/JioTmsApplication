package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InvoiceRefMapperTest {

    private InvoiceRefMapper invoiceRefMapper;

    @BeforeEach
    public void setUp() {
        invoiceRefMapper = new InvoiceRefMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(invoiceRefMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invoiceRefMapper.fromId(null)).isNull();
    }
}
