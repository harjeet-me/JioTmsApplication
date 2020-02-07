package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.ContainerService;
import com.jio.tms.v1.domain.Container;
import com.jio.tms.v1.repository.ContainerRepository;
import com.jio.tms.v1.repository.search.ContainerSearchRepository;
import com.jio.tms.v1.service.dto.ContainerDTO;
import com.jio.tms.v1.service.mapper.ContainerMapper;
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
 * Service Implementation for managing {@link Container}.
 */
@Service
@Transactional
public class ContainerServiceImpl implements ContainerService {

    private final Logger log = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerRepository containerRepository;

    private final ContainerMapper containerMapper;

    private final ContainerSearchRepository containerSearchRepository;

    public ContainerServiceImpl(ContainerRepository containerRepository, ContainerMapper containerMapper, ContainerSearchRepository containerSearchRepository) {
        this.containerRepository = containerRepository;
        this.containerMapper = containerMapper;
        this.containerSearchRepository = containerSearchRepository;
    }

    /**
     * Save a container.
     *
     * @param containerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContainerDTO save(ContainerDTO containerDTO) {
        log.debug("Request to save Container : {}", containerDTO);
        Container container = containerMapper.toEntity(containerDTO);
        container = containerRepository.save(container);
        ContainerDTO result = containerMapper.toDto(container);
        containerSearchRepository.save(container);
        return result;
    }

    /**
     * Get all the containers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContainerDTO> findAll() {
        log.debug("Request to get all Containers");
        return containerRepository.findAll().stream()
            .map(containerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one container by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContainerDTO> findOne(Long id) {
        log.debug("Request to get Container : {}", id);
        return containerRepository.findById(id)
            .map(containerMapper::toDto);
    }

    /**
     * Delete the container by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Container : {}", id);
        containerRepository.deleteById(id);
        containerSearchRepository.deleteById(id);
    }

    /**
     * Search for the container corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContainerDTO> search(String query) {
        log.debug("Request to search Containers for query {}", query);
        return StreamSupport
            .stream(containerSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(containerMapper::toDto)
            .collect(Collectors.toList());
    }
}
