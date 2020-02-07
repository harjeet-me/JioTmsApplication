package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.ContainerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Container} and its DTO {@link ContainerDTO}.
 */
@Mapper(componentModel = "spring", uses = {TripMapper.class})
public interface ContainerMapper extends EntityMapper<ContainerDTO, Container> {

    @Mapping(source = "trip.id", target = "tripId")
    ContainerDTO toDto(Container container);

    @Mapping(source = "tripId", target = "trip")
    Container toEntity(ContainerDTO containerDTO);

    default Container fromId(Long id) {
        if (id == null) {
            return null;
        }
        Container container = new Container();
        container.setId(id);
        return container;
    }
}
