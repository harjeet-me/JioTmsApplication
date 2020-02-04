package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class InvoiceRefDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceRefDTO.class);
        InvoiceRefDTO invoiceRefDTO1 = new InvoiceRefDTO();
        invoiceRefDTO1.setId(1L);
        InvoiceRefDTO invoiceRefDTO2 = new InvoiceRefDTO();
        assertThat(invoiceRefDTO1).isNotEqualTo(invoiceRefDTO2);
        invoiceRefDTO2.setId(invoiceRefDTO1.getId());
        assertThat(invoiceRefDTO1).isEqualTo(invoiceRefDTO2);
        invoiceRefDTO2.setId(2L);
        assertThat(invoiceRefDTO1).isNotEqualTo(invoiceRefDTO2);
        invoiceRefDTO1.setId(null);
        assertThat(invoiceRefDTO1).isNotEqualTo(invoiceRefDTO2);
    }
}
