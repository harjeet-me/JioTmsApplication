package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.InvoiceRefService;
import com.jio.tms.v1.domain.InvoiceRef;
import com.jio.tms.v1.repository.InvoiceRefRepository;
import com.jio.tms.v1.repository.search.InvoiceRefSearchRepository;
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
 * Service Implementation for managing {@link InvoiceRef}.
 */
@Service
@Transactional
public class InvoiceRefServiceImpl implements InvoiceRefService {

    private final Logger log = LoggerFactory.getLogger(InvoiceRefServiceImpl.class);

    private final InvoiceRefRepository invoiceRefRepository;

    private final InvoiceRefSearchRepository invoiceRefSearchRepository;

    public InvoiceRefServiceImpl(InvoiceRefRepository invoiceRefRepository, InvoiceRefSearchRepository invoiceRefSearchRepository) {
        this.invoiceRefRepository = invoiceRefRepository;
        this.invoiceRefSearchRepository = invoiceRefSearchRepository;
    }

    /**
     * Save a invoiceRef.
     *
     * @param invoiceRef the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoiceRef save(InvoiceRef invoiceRef) {
        log.debug("Request to save InvoiceRef : {}", invoiceRef);
        InvoiceRef result = invoiceRefRepository.save(invoiceRef);
        invoiceRefSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the invoiceRefs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvoiceRef> findAll() {
        log.debug("Request to get all InvoiceRefs");
        return invoiceRefRepository.findAll();
    }


    /**
     * Get one invoiceRef by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceRef> findOne(Long id) {
        log.debug("Request to get InvoiceRef : {}", id);
        return invoiceRefRepository.findById(id);
    }

    /**
     * Delete the invoiceRef by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceRef : {}", id);
        invoiceRefRepository.deleteById(id);
        invoiceRefSearchRepository.deleteById(id);
    }

    /**
     * Search for the invoiceRef corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvoiceRef> search(String query) {
        log.debug("Request to search InvoiceRefs for query {}", query);
        return StreamSupport
            .stream(invoiceRefSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
