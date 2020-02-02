package com.jio.tms.v1.service;

import com.jio.tms.v1.domain.Email;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Email}.
 */
public interface EmailService {

    /**
     * Save a email.
     *
     * @param email the entity to save.
     * @return the persisted entity.
     */
    Email save(Email email);

    /**
     * Get all the emails.
     *
     * @return the list of entities.
     */
    List<Email> findAll();


    /**
     * Get the "id" email.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Email> findOne(Long id);

    /**
     * Delete the "id" email.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the email corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<Email> search(String query);
}
