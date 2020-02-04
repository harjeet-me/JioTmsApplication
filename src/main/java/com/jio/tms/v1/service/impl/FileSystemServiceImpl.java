package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.FileSystemService;
import com.jio.tms.v1.domain.FileSystem;
import com.jio.tms.v1.repository.FileSystemRepository;
import com.jio.tms.v1.repository.search.FileSystemSearchRepository;
import com.jio.tms.v1.service.dto.FileSystemDTO;
import com.jio.tms.v1.service.mapper.FileSystemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link FileSystem}.
 */
@Service
@Transactional
public class FileSystemServiceImpl implements FileSystemService {

    private final Logger log = LoggerFactory.getLogger(FileSystemServiceImpl.class);

    private final FileSystemRepository fileSystemRepository;

    private final FileSystemMapper fileSystemMapper;

    private final FileSystemSearchRepository fileSystemSearchRepository;

    public FileSystemServiceImpl(FileSystemRepository fileSystemRepository, FileSystemMapper fileSystemMapper, FileSystemSearchRepository fileSystemSearchRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.fileSystemMapper = fileSystemMapper;
        this.fileSystemSearchRepository = fileSystemSearchRepository;
    }

    /**
     * Save a fileSystem.
     *
     * @param fileSystemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileSystemDTO save(FileSystemDTO fileSystemDTO) {
        log.debug("Request to save FileSystem : {}", fileSystemDTO);
        FileSystem fileSystem = fileSystemMapper.toEntity(fileSystemDTO);
        fileSystem = fileSystemRepository.save(fileSystem);
        FileSystemDTO result = fileSystemMapper.toDto(fileSystem);
        fileSystemSearchRepository.save(fileSystem);
        return result;
    }

    /**
     * Get all the fileSystems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileSystemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileSystems");
        return fileSystemRepository.findAll(pageable)
            .map(fileSystemMapper::toDto);
    }


    /**
     * Get one fileSystem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileSystemDTO> findOne(Long id) {
        log.debug("Request to get FileSystem : {}", id);
        return fileSystemRepository.findById(id)
            .map(fileSystemMapper::toDto);
    }

    /**
     * Delete the fileSystem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileSystem : {}", id);
        fileSystemRepository.deleteById(id);
        fileSystemSearchRepository.deleteById(id);
    }

    /**
     * Search for the fileSystem corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileSystemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FileSystems for query {}", query);
        return fileSystemSearchRepository.search(queryStringQuery(query), pageable)
            .map(fileSystemMapper::toDto);
    }
}
