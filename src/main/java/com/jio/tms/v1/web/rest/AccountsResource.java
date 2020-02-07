package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.AccountsService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.AccountsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.jio.tms.v1.domain.Accounts}.
 */
@RestController
@RequestMapping("/api")
public class AccountsResource {

    private final Logger log = LoggerFactory.getLogger(AccountsResource.class);

    private static final String ENTITY_NAME = "accounts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountsService accountsService;

    public AccountsResource(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    /**
     * {@code POST  /accounts} : Create a new accounts.
     *
     * @param accountsDTO the accountsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountsDTO, or with status {@code 400 (Bad Request)} if the accounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accounts")
    public ResponseEntity<AccountsDTO> createAccounts(@RequestBody AccountsDTO accountsDTO) throws URISyntaxException {
        log.debug("REST request to save Accounts : {}", accountsDTO);
        if (accountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new accounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountsDTO result = accountsService.save(accountsDTO);
        return ResponseEntity.created(new URI("/api/accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /accounts} : Updates an existing accounts.
     *
     * @param accountsDTO the accountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountsDTO,
     * or with status {@code 400 (Bad Request)} if the accountsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/accounts")
    public ResponseEntity<AccountsDTO> updateAccounts(@RequestBody AccountsDTO accountsDTO) throws URISyntaxException {
        log.debug("REST request to update Accounts : {}", accountsDTO);
        if (accountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountsDTO result = accountsService.save(accountsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /accounts} : get all the accounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accounts in body.
     */
    @GetMapping("/accounts")
    public List<AccountsDTO> getAllAccounts() {
        log.debug("REST request to get all Accounts");
        return accountsService.findAll();
    }

    /**
     * {@code GET  /accounts/:id} : get the "id" accounts.
     *
     * @param id the id of the accountsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountsDTO> getAccounts(@PathVariable Long id) {
        log.debug("REST request to get Accounts : {}", id);
        Optional<AccountsDTO> accountsDTO = accountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountsDTO);
    }

    /**
     * {@code DELETE  /accounts/:id} : delete the "id" accounts.
     *
     * @param id the id of the accountsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccounts(@PathVariable Long id) {
        log.debug("REST request to delete Accounts : {}", id);
        accountsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/accounts?query=:query} : search for the accounts corresponding
     * to the query.
     *
     * @param query the query of the accounts search.
     * @return the result of the search.
     */
    @GetMapping("/_search/accounts")
    public List<AccountsDTO> searchAccounts(@RequestParam String query) {
        log.debug("REST request to search Accounts for query {}", query);
        return accountsService.search(query);
    }
}
