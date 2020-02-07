package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.FilesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Files}.
 */
public interface FilesService {

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save.
     * @return the persisted entity.
     */
    FilesDTO save(FilesDTO filesDTO);

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    List<FilesDTO> findAll();

    /**
     * Get the "id" files.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FilesDTO> findOne(Long id);

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
    List<FilesDTO> search(String query);
}
