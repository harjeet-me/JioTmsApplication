package com.jio.tms.v1.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class InvoiceRefTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceRef.class);
        InvoiceRef invoiceRef1 = new InvoiceRef();
        invoiceRef1.setId(1L);
        InvoiceRef invoiceRef2 = new InvoiceRef();
        invoiceRef2.setId(invoiceRef1.getId());
        assertThat(invoiceRef1).isEqualTo(invoiceRef2);
        invoiceRef2.setId(2L);
        assertThat(invoiceRef1).isNotEqualTo(invoiceRef2);
        invoiceRef1.setId(null);
        assertThat(invoiceRef1).isNotEqualTo(invoiceRef2);
    }
}
