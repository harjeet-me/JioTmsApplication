package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.AccountsService;
import com.jio.tms.v1.domain.Accounts;
import com.jio.tms.v1.repository.AccountsRepository;
import com.jio.tms.v1.repository.search.AccountsSearchRepository;
import com.jio.tms.v1.service.dto.AccountsDTO;
import com.jio.tms.v1.service.mapper.AccountsMapper;
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
 * Service Implementation for managing {@link Accounts}.
 */
@Service
@Transactional
public class AccountsServiceImpl implements AccountsService {

    private final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private final AccountsRepository accountsRepository;

    private final AccountsMapper accountsMapper;

    private final AccountsSearchRepository accountsSearchRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository, AccountsMapper accountsMapper, AccountsSearchRepository accountsSearchRepository) {
        this.accountsRepository = accountsRepository;
        this.accountsMapper = accountsMapper;
        this.accountsSearchRepository = accountsSearchRepository;
    }

    /**
     * Save a accounts.
     *
     * @param accountsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountsDTO save(AccountsDTO accountsDTO) {
        log.debug("Request to save Accounts : {}", accountsDTO);
        Accounts accounts = accountsMapper.toEntity(accountsDTO);
        accounts = accountsRepository.save(accounts);
        AccountsDTO result = accountsMapper.toDto(accounts);
        accountsSearchRepository.save(accounts);
        return result;
    }

    /**
     * Get all the accounts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountsDTO> findAll() {
        log.debug("Request to get all Accounts");
        return accountsRepository.findAll().stream()
            .map(accountsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountsDTO> findOne(Long id) {
        log.debug("Request to get Accounts : {}", id);
        return accountsRepository.findById(id)
            .map(accountsMapper::toDto);
    }

    /**
     * Delete the accounts by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Accounts : {}", id);
        accountsRepository.deleteById(id);
        accountsSearchRepository.deleteById(id);
    }

    /**
     * Search for the accounts corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountsDTO> search(String query) {
        log.debug("Request to search Accounts for query {}", query);
        return StreamSupport
            .stream(accountsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(accountsMapper::toDto)
            .collect(Collectors.toList());
    }
}
