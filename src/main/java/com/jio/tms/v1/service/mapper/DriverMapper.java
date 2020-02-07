package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.DriverDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Driver} and its DTO {@link DriverDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {


    @Mapping(target = "trips", ignore = true)
    @Mapping(target = "removeTrip", ignore = true)
    Driver toEntity(DriverDTO driverDTO);

    default Driver fromId(Long id) {
        if (id == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setId(id);
        return driver;
    }
}
