package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.EquipmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {InsuranceMapper.class})
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {

    @Mapping(source = "insurance.id", target = "insuranceId")
    EquipmentDTO toDto(Equipment equipment);

    @Mapping(source = "insuranceId", target = "insurance")
    @Mapping(target = "trips", ignore = true)
    @Mapping(target = "removeTrip", ignore = true)
    Equipment toEntity(EquipmentDTO equipmentDTO);

    default Equipment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Equipment equipment = new Equipment();
        equipment.setId(id);
        return equipment;
    }
}
