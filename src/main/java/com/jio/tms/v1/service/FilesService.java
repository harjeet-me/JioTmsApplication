package com.jio.tms.v1.service;

import com.jio.tms.v1.domain.Files;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Files}.
 */
public interface FilesService {

    /**
     * Save a files.
     *
     * @param files the entity to save.
     * @return the persisted entity.
     */
    Files save(Files files);

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    List<Files> findAll();


    /**
     * Get the "id" files.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Files> findOne(Long id);

    /**
     * Delete the "id" files.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the files corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<Files> search(String query);
}
