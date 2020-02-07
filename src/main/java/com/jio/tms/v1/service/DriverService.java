package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.DriverDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Driver}.
 */
public interface DriverService {

    /**
     * Save a driver.
     *
     * @param driverDTO the entity to save.
     * @return the persisted entity.
     */
    DriverDTO save(DriverDTO driverDTO);

    /**
     * Get all the drivers.
     *
     * @return the list of entities.
     */
    List<DriverDTO> findAll();

    /**
     * Get the "id" driver.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DriverDTO> findOne(Long id);

    /**
     * Delete the "id" driver.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the driver corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<DriverDTO> search(String query);
}
