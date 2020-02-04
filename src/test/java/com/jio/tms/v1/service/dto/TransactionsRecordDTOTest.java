package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class TransactionsRecordDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionsRecordDTO.class);
        TransactionsRecordDTO transactionsRecordDTO1 = new TransactionsRecordDTO();
        transactionsRecordDTO1.setId(1L);
        TransactionsRecordDTO transactionsRecordDTO2 = new TransactionsRecordDTO();
        assertThat(transactionsRecordDTO1).isNotEqualTo(transactionsRecordDTO2);
        transactionsRecordDTO2.setId(transactionsRecordDTO1.getId());
        assertThat(transactionsRecordDTO1).isEqualTo(transactionsRecordDTO2);
        transactionsRecordDTO2.setId(2L);
        assertThat(transactionsRecordDTO1).isNotEqualTo(transactionsRecordDTO2);
        transactionsRecordDTO1.setId(null);
        assertThat(transactionsRecordDTO1).isNotEqualTo(transactionsRecordDTO2);
    }
}
