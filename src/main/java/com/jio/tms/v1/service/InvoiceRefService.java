package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.InvoiceRefDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.InvoiceRef}.
 */
public interface InvoiceRefService {

    /**
     * Save a invoiceRef.
     *
     * @param invoiceRefDTO the entity to save.
     * @return the persisted entity.
     */
    InvoiceRefDTO save(InvoiceRefDTO invoiceRefDTO);

    /**
     * Get all the invoiceRefs.
     *
     * @return the list of entities.
     */
    List<InvoiceRefDTO> findAll();

    /**
     * Get the "id" invoiceRef.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceRefDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceRef.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the invoiceRef corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<InvoiceRefDTO> search(String query);
}
