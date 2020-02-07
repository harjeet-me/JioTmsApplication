package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.ContainerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Container}.
 */
public interface ContainerService {

    /**
     * Save a container.
     *
     * @param containerDTO the entity to save.
     * @return the persisted entity.
     */
    ContainerDTO save(ContainerDTO containerDTO);

    /**
     * Get all the containers.
     *
     * @return the list of entities.
     */
    List<ContainerDTO> findAll();

    /**
     * Get the "id" container.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContainerDTO> findOne(Long id);

    /**
     * Delete the "id" container.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the container corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<ContainerDTO> search(String query);
}
