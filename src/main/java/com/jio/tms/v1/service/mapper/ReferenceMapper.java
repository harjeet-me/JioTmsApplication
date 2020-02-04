package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.ReferenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reference} and its DTO {@link ReferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenceMapper extends EntityMapper<ReferenceDTO, Reference> {



    default Reference fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reference reference = new Reference();
        reference.setId(id);
        return reference;
    }
}
