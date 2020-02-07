package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.FileSystemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.FileSystem}.
 */
public interface FileSystemService {

    /**
     * Save a fileSystem.
     *
     * @param fileSystemDTO the entity to save.
     * @return the persisted entity.
     */
    FileSystemDTO save(FileSystemDTO fileSystemDTO);

    /**
     * Get all the fileSystems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileSystemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileSystem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileSystemDTO> findOne(Long id);

    /**
     * Delete the "id" fileSystem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the fileSystem corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileSystemDTO> search(String query, Pageable pageable);
}
