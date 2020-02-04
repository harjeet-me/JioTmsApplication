package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountsMapperTest {

    private AccountsMapper accountsMapper;

    @BeforeEach
    public void setUp() {
        accountsMapper = new AccountsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountsMapper.fromId(null)).isNull();
    }
}
