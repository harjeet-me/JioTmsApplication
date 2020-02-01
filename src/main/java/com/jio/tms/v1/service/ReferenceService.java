package com.jio.tms.v1.service;

import com.jio.tms.v1.domain.Reference;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Reference}.
 */
public interface ReferenceService {

    /**
     * Save a reference.
     *
     * @param reference the entity to save.
     * @return the persisted entity.
     */
    Reference save(Reference reference);

    /**
     * Get all the references.
     *
     * @return the list of entities.
     */
    List<Reference> findAll();


    /**
     * Get the "id" reference.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Reference> findOne(Long id);

    /**
     * Delete the "id" reference.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the reference corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<Reference> search(String query);
}
