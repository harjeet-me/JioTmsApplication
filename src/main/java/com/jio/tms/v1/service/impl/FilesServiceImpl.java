package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.FilesService;
import com.jio.tms.v1.domain.Files;
import com.jio.tms.v1.repository.FilesRepository;
import com.jio.tms.v1.repository.search.FilesSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Files}.
 */
@Service
@Transactional
public class FilesServiceImpl implements FilesService {

    private final Logger log = LoggerFactory.getLogger(FilesServiceImpl.class);

    private final FilesRepository filesRepository;

    private final FilesSearchRepository filesSearchRepository;

    public FilesServiceImpl(FilesRepository filesRepository, FilesSearchRepository filesSearchRepository) {
        this.filesRepository = filesRepository;
        this.filesSearchRepository = filesSearchRepository;
    }

    /**
     * Save a files.
     *
     * @param files the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Files save(Files files) {
        log.debug("Request to save Files : {}", files);
        Files result = filesRepository.save(files);
        filesSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Files> findAll() {
        log.debug("Request to get all Files");
        return filesRepository.findAll();
    }


    /**
     * Get one files by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Files> findOne(Long id) {
        log.debug("Request to get Files : {}", id);
        return filesRepository.findById(id);
    }

    /**
     * Delete the files by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Files : {}", id);
        filesRepository.deleteById(id);
        filesSearchRepository.deleteById(id);
    }

    /**
     * Search for the files corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Files> search(String query) {
        log.debug("Request to search Files for query {}", query);
        return StreamSupport
            .stream(filesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
