package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.TransactionsRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.TransactionsRecord}.
 */
public interface TransactionsRecordService {

    /**
     * Save a transactionsRecord.
     *
     * @param transactionsRecordDTO the entity to save.
     * @return the persisted entity.
     */
    TransactionsRecordDTO save(TransactionsRecordDTO transactionsRecordDTO);

    /**
     * Get all the transactionsRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TransactionsRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transactionsRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionsRecordDTO> findOne(Long id);

    /**
     * Delete the "id" transactionsRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the transactionsRecord corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TransactionsRecordDTO> search(String query, Pageable pageable);
}
