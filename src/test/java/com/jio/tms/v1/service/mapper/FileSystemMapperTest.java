package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemMapperTest {

    private FileSystemMapper fileSystemMapper;

    @BeforeEach
    public void setUp() {
        fileSystemMapper = new FileSystemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fileSystemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fileSystemMapper.fromId(null)).isNull();
    }
}
