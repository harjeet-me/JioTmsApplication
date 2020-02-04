package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.OwnerOperatorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OwnerOperator} and its DTO {@link OwnerOperatorDTO}.
 */
@Mapper(componentModel = "spring", uses = {InsuranceMapper.class})
public interface OwnerOperatorMapper extends EntityMapper<OwnerOperatorDTO, OwnerOperator> {

    @Mapping(source = "operInsurance.id", target = "operInsuranceId")
    OwnerOperatorDTO toDto(OwnerOperator ownerOperator);

    @Mapping(source = "operInsuranceId", target = "operInsurance")
    @Mapping(target = "loadOrders", ignore = true)
    @Mapping(target = "removeLoadOrder", ignore = true)
    OwnerOperator toEntity(OwnerOperatorDTO ownerOperatorDTO);

    default OwnerOperator fromId(Long id) {
        if (id == null) {
            return null;
        }
        OwnerOperator ownerOperator = new OwnerOperator();
        ownerOperator.setId(id);
        return ownerOperator;
    }
}
