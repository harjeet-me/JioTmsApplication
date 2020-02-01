package com.jio.tms.v1.service;

import com.jio.tms.v1.domain.InvoiceRef;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link InvoiceRef}.
 */
public interface InvoiceRefService {

    /**
     * Save a invoiceRef.
     *
     * @param invoiceRef the entity to save.
     * @return the persisted entity.
     */
    InvoiceRef save(InvoiceRef invoiceRef);

    /**
     * Get all the invoiceRefs.
     *
     * @return the list of entities.
     */
    List<InvoiceRef> findAll();


    /**
     * Get the "id" invoiceRef.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceRef> findOne(Long id);

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
    List<InvoiceRef> search(String query);
}
