package com.jio.tms.v1.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jio.tms.v1.web.rest.TestUtil;

public class FileSystemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileSystemDTO.class);
        FileSystemDTO fileSystemDTO1 = new FileSystemDTO();
        fileSystemDTO1.setId(1L);
        FileSystemDTO fileSystemDTO2 = new FileSystemDTO();
        assertThat(fileSystemDTO1).isNotEqualTo(fileSystemDTO2);
        fileSystemDTO2.setId(fileSystemDTO1.getId());
        assertThat(fileSystemDTO1).isEqualTo(fileSystemDTO2);
        fileSystemDTO2.setId(2L);
        assertThat(fileSystemDTO1).isNotEqualTo(fileSystemDTO2);
        fileSystemDTO1.setId(null);
        assertThat(fileSystemDTO1).isNotEqualTo(fileSystemDTO2);
    }
}
