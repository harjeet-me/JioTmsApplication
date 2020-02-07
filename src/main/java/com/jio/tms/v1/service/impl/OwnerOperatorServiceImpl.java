package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.OwnerOperatorService;
import com.jio.tms.v1.domain.OwnerOperator;
import com.jio.tms.v1.repository.OwnerOperatorRepository;
import com.jio.tms.v1.repository.search.OwnerOperatorSearchRepository;
import com.jio.tms.v1.service.dto.OwnerOperatorDTO;
import com.jio.tms.v1.service.mapper.OwnerOperatorMapper;
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
 * Service Implementation for managing {@link OwnerOperator}.
 */
@Service
@Transactional
public class OwnerOperatorServiceImpl implements OwnerOperatorService {

    private final Logger log = LoggerFactory.getLogger(OwnerOperatorServiceImpl.class);

    private final OwnerOperatorRepository ownerOperatorRepository;

    private final OwnerOperatorMapper ownerOperatorMapper;

    private final OwnerOperatorSearchRepository ownerOperatorSearchRepository;

    public OwnerOperatorServiceImpl(OwnerOperatorRepository ownerOperatorRepository, OwnerOperatorMapper ownerOperatorMapper, OwnerOperatorSearchRepository ownerOperatorSearchRepository) {
        this.ownerOperatorRepository = ownerOperatorRepository;
        this.ownerOperatorMapper = ownerOperatorMapper;
        this.ownerOperatorSearchRepository = ownerOperatorSearchRepository;
    }

    /**
     * Save a ownerOperator.
     *
     * @param ownerOperatorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OwnerOperatorDTO save(OwnerOperatorDTO ownerOperatorDTO) {
        log.debug("Request to save OwnerOperator : {}", ownerOperatorDTO);
        OwnerOperator ownerOperator = ownerOperatorMapper.toEntity(ownerOperatorDTO);
        ownerOperator = ownerOperatorRepository.save(ownerOperator);
        OwnerOperatorDTO result = ownerOperatorMapper.toDto(ownerOperator);
        ownerOperatorSearchRepository.save(ownerOperator);
        return result;
    }

    /**
     * Get all the ownerOperators.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OwnerOperatorDTO> findAll() {
        log.debug("Request to get all OwnerOperators");
        return ownerOperatorRepository.findAll().stream()
            .map(ownerOperatorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ownerOperator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerOperatorDTO> findOne(Long id) {
        log.debug("Request to get OwnerOperator : {}", id);
        return ownerOperatorRepository.findById(id)
            .map(ownerOperatorMapper::toDto);
    }

    /**
     * Delete the ownerOperator by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OwnerOperator : {}", id);
        ownerOperatorRepository.deleteById(id);
        ownerOperatorSearchRepository.deleteById(id);
    }

    /**
     * Search for the ownerOperator corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OwnerOperatorDTO> search(String query) {
        log.debug("Request to search OwnerOperators for query {}", query);
        return StreamSupport
            .stream(ownerOperatorSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(ownerOperatorMapper::toDto)
            .collect(Collectors.toList());
    }
}
