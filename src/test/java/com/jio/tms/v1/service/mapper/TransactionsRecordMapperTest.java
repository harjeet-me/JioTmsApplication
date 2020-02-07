package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionsRecordMapperTest {

    private TransactionsRecordMapper transactionsRecordMapper;

    @BeforeEach
    public void setUp() {
        transactionsRecordMapper = new TransactionsRecordMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transactionsRecordMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transactionsRecordMapper.fromId(null)).isNull();
    }
}
