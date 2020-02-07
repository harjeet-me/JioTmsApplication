package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.InvoiceItemService;
import com.jio.tms.v1.domain.InvoiceItem;
import com.jio.tms.v1.repository.InvoiceItemRepository;
import com.jio.tms.v1.repository.search.InvoiceItemSearchRepository;
import com.jio.tms.v1.service.dto.InvoiceItemDTO;
import com.jio.tms.v1.service.mapper.InvoiceItemMapper;
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
 * Service Implementation for managing {@link InvoiceItem}.
 */
@Service
@Transactional
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final Logger log = LoggerFactory.getLogger(InvoiceItemServiceImpl.class);

    private final InvoiceItemRepository invoiceItemRepository;

    private final InvoiceItemMapper invoiceItemMapper;

    private final InvoiceItemSearchRepository invoiceItemSearchRepository;

    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository, InvoiceItemMapper invoiceItemMapper, InvoiceItemSearchRepository invoiceItemSearchRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceItemMapper = invoiceItemMapper;
        this.invoiceItemSearchRepository = invoiceItemSearchRepository;
    }

    /**
     * Save a invoiceItem.
     *
     * @param invoiceItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoiceItemDTO save(InvoiceItemDTO invoiceItemDTO) {
        log.debug("Request to save InvoiceItem : {}", invoiceItemDTO);
        InvoiceItem invoiceItem = invoiceItemMapper.toEntity(invoiceItemDTO);
        invoiceItem = invoiceItemRepository.save(invoiceItem);
        InvoiceItemDTO result = invoiceItemMapper.toDto(invoiceItem);
        invoiceItemSearchRepository.save(invoiceItem);
        return result;
    }

    /**
     * Get all the invoiceItems.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvoiceItemDTO> findAll() {
        log.debug("Request to get all InvoiceItems");
        return invoiceItemRepository.findAll().stream()
            .map(invoiceItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one invoiceItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceItemDTO> findOne(Long id) {
        log.debug("Request to get InvoiceItem : {}", id);
        return invoiceItemRepository.findById(id)
            .map(invoiceItemMapper::toDto);
    }

    /**
     * Delete the invoiceItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceItem : {}", id);
        invoiceItemRepository.deleteById(id);
        invoiceItemSearchRepository.deleteById(id);
    }

    /**
     * Search for the invoiceItem corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvoiceItemDTO> search(String query) {
        log.debug("Request to search InvoiceItems for query {}", query);
        return StreamSupport
            .stream(invoiceItemSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(invoiceItemMapper::toDto)
            .collect(Collectors.toList());
    }
}
