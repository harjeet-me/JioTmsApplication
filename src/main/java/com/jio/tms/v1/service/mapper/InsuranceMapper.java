package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.InsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Insurance} and its DTO {@link InsuranceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceMapper extends EntityMapper<InsuranceDTO, Insurance> {


    @Mapping(target = "ownerOperator", ignore = true)
    Insurance toEntity(InsuranceDTO insuranceDTO);

    default Insurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(id);
        return insurance;
    }
}
