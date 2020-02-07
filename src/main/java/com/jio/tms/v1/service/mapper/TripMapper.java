package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.TripDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trip} and its DTO {@link TripDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, CustomerMapper.class, DriverMapper.class, EquipmentMapper.class, OwnerOperatorMapper.class})
public interface TripMapper extends EntityMapper<TripDTO, Trip> {

    @Mapping(source = "pickupLocation.id", target = "pickupLocationId")
    @Mapping(source = "pickupLocation.address", target = "pickupLocationAddress")
    @Mapping(source = "dropLocation.id", target = "dropLocationId")
    @Mapping(source = "dropLocation.address", target = "dropLocationAddress")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "ownerOperator.id", target = "ownerOperatorId")
    TripDTO toDto(Trip trip);

    @Mapping(source = "pickupLocationId", target = "pickupLocation")
    @Mapping(source = "dropLocationId", target = "dropLocation")
    @Mapping(target = "invoices", ignore = true)
    @Mapping(target = "removeInvoice", ignore = true)
    @Mapping(target = "containers", ignore = true)
    @Mapping(target = "removeContainer", ignore = true)
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "driverId", target = "driver")
    @Mapping(source = "equipmentId", target = "equipment")
    @Mapping(source = "ownerOperatorId", target = "ownerOperator")
    Trip toEntity(TripDTO tripDTO);

    default Trip fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }
}
