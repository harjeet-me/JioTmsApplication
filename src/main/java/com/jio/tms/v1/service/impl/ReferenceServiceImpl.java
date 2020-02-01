package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.ReferenceService;
import com.jio.tms.v1.domain.Reference;
import com.jio.tms.v1.repository.ReferenceRepository;
import com.jio.tms.v1.repository.search.ReferenceSearchRepository;
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
 * Service Implementation for managing {@link Reference}.
 */
@Service
@Transactional
public class ReferenceServiceImpl implements ReferenceService {

    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private final ReferenceRepository referenceRepository;

    private final ReferenceSearchRepository referenceSearchRepository;

    public ReferenceServiceImpl(ReferenceRepository referenceRepository, ReferenceSearchRepository referenceSearchRepository) {
        this.referenceRepository = referenceRepository;
        this.referenceSearchRepository = referenceSearchRepository;
    }

    /**
     * Save a reference.
     *
     * @param reference the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Reference save(Reference reference) {
        log.debug("Request to save Reference : {}", reference);
        Reference result = referenceRepository.save(reference);
        referenceSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the references.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Reference> findAll() {
        log.debug("Request to get all References");
        return referenceRepository.findAll();
    }


    /**
     * Get one reference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Reference> findOne(Long id) {
        log.debug("Request to get Reference : {}", id);
        return referenceRepository.findById(id);
    }

    /**
     * Delete the reference by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reference : {}", id);
        referenceRepository.deleteById(id);
        referenceSearchRepository.deleteById(id);
    }

    /**
     * Search for the reference corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Reference> search(String query) {
        log.debug("Request to search References for query {}", query);
        return StreamSupport
            .stream(referenceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
