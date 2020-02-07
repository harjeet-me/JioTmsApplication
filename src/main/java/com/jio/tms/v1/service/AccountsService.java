package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.AccountsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Accounts}.
 */
public interface AccountsService {

    /**
     * Save a accounts.
     *
     * @param accountsDTO the entity to save.
     * @return the persisted entity.
     */
    AccountsDTO save(AccountsDTO accountsDTO);

    /**
     * Get all the accounts.
     *
     * @return the list of entities.
     */
    List<AccountsDTO> findAll();

    /**
     * Get the "id" accounts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountsDTO> findOne(Long id);

    /**
     * Delete the "id" accounts.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the accounts corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<AccountsDTO> search(String query);
}
