package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class EmailDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailDTO.class);
        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setId(1L);
        EmailDTO emailDTO2 = new EmailDTO();
        assertThat(emailDTO1).isNotEqualTo(emailDTO2);
        emailDTO2.setId(emailDTO1.getId());
        assertThat(emailDTO1).isEqualTo(emailDTO2);
        emailDTO2.setId(2L);
        assertThat(emailDTO1).isNotEqualTo(emailDTO2);
        emailDTO1.setId(null);
        assertThat(emailDTO1).isNotEqualTo(emailDTO2);
    }
}