package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.OwnerOperatorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.OwnerOperator}.
 */
public interface OwnerOperatorService {

    /**
     * Save a ownerOperator.
     *
     * @param ownerOperatorDTO the entity to save.
     * @return the persisted entity.
     */
    OwnerOperatorDTO save(OwnerOperatorDTO ownerOperatorDTO);

    /**
     * Get all the ownerOperators.
     *
     * @return the list of entities.
     */
    List<OwnerOperatorDTO> findAll();


    /**
     * Get the "id" ownerOperator.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OwnerOperatorDTO> findOne(Long id);

    /**
     * Delete the "id" ownerOperator.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the ownerOperator corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<OwnerOperatorDTO> search(String query);
}
