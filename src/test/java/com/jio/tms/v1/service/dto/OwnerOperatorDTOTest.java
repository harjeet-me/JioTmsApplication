package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class OwnerOperatorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OwnerOperatorDTO.class);
        OwnerOperatorDTO ownerOperatorDTO1 = new OwnerOperatorDTO();
        ownerOperatorDTO1.setId(1L);
        OwnerOperatorDTO ownerOperatorDTO2 = new OwnerOperatorDTO();
        assertThat(ownerOperatorDTO1).isNotEqualTo(ownerOperatorDTO2);
        ownerOperatorDTO2.setId(ownerOperatorDTO1.getId());
        assertThat(ownerOperatorDTO1).isEqualTo(ownerOperatorDTO2);
        ownerOperatorDTO2.setId(2L);
        assertThat(ownerOperatorDTO1).isNotEqualTo(ownerOperatorDTO2);
        ownerOperatorDTO1.setId(null);
        assertThat(ownerOperatorDTO1).isNotEqualTo(ownerOperatorDTO2);
    }
}
