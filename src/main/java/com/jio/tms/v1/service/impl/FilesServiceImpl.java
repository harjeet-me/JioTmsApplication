package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.FilesService;
import com.jio.tms.v1.domain.Files;
import com.jio.tms.v1.repository.FilesRepository;
import com.jio.tms.v1.repository.search.FilesSearchRepository;
import com.jio.tms.v1.service.dto.FilesDTO;
import com.jio.tms.v1.service.mapper.FilesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    private final FilesMapper filesMapper;

    private final FilesSearchRepository filesSearchRepository;

    public FilesServiceImpl(FilesRepository filesRepository, FilesMapper filesMapper, FilesSearchRepository filesSearchRepository) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
        this.filesSearchRepository = filesSearchRepository;
    }

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FilesDTO save(FilesDTO filesDTO) {
        log.debug("Request to save Files : {}", filesDTO);
        Files files = filesMapper.toEntity(filesDTO);
        files = filesRepository.save(files);
        FilesDTO result = filesMapper.toDto(files);
        filesSearchRepository.save(files);
        return result;
    }

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilesDTO> findAll() {
        log.debug("Request to get all Files");
        return filesRepository.findAll().stream()
            .map(filesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one files by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FilesDTO> findOne(Long id) {
        log.debug("Request to get Files : {}", id);
        return filesRepository.findById(id)
            .map(filesMapper::toDto);
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
    public List<FilesDTO> search(String query) {
        log.debug("Request to search Files for query {}", query);
        return StreamSupport
            .stream(filesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(filesMapper::toDto)
            .collect(Collectors.toList());
    }
}
