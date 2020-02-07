package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.EmailDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Email}.
 */
public interface EmailService {

    /**
     * Save a email.
     *
     * @param emailDTO the entity to save.
     * @return the persisted entity.
     */
    EmailDTO save(EmailDTO emailDTO);

    /**
     * Get all the emails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmailDTO> findAll(Pageable pageable);

    /**
     * Get the "id" email.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmailDTO> findOne(Long id);

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmailDTO> search(String query, Pageable pageable);
}
