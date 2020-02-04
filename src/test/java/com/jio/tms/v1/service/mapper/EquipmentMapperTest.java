package com.jio.tms.v1.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EquipmentMapperTest {

    private EquipmentMapper equipmentMapper;

    @BeforeEach
    public void setUp() {
        equipmentMapper = new EquipmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(equipmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(equipmentMapper.fromId(null)).isNull();
    }
}
