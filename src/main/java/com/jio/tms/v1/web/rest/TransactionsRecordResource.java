package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.TransactionsRecordService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.TransactionsRecordDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.jio.tms.v1.domain.TransactionsRecord}.
 */
@RestController
@RequestMapping("/api")
public class TransactionsRecordResource {

    private final Logger log = LoggerFactory.getLogger(TransactionsRecordResource.class);

    private static final String ENTITY_NAME = "transactionsRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionsRecordService transactionsRecordService;

    public TransactionsRecordResource(TransactionsRecordService transactionsRecordService) {
        this.transactionsRecordService = transactionsRecordService;
    }

    /**
     * {@code POST  /transactions-records} : Create a new transactionsRecord.
     *
     * @param transactionsRecordDTO the transactionsRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionsRecordDTO, or with status {@code 400 (Bad Request)} if the transactionsRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transactions-records")
    public ResponseEntity<TransactionsRecordDTO> createTransactionsRecord(@RequestBody TransactionsRecordDTO transactionsRecordDTO) throws URISyntaxException {
        log.debug("REST request to save TransactionsRecord : {}", transactionsRecordDTO);
        if (transactionsRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new transactionsRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionsRecordDTO result = transactionsRecordService.save(transactionsRecordDTO);
        return ResponseEntity.created(new URI("/api/transactions-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transactions-records} : Updates an existing transactionsRecord.
     *
     * @param transactionsRecordDTO the transactionsRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionsRecordDTO,
     * or with status {@code 400 (Bad Request)} if the transactionsRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionsRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transactions-records")
    public ResponseEntity<TransactionsRecordDTO> updateTransactionsRecord(@RequestBody TransactionsRecordDTO transactionsRecordDTO) throws URISyntaxException {
        log.debug("REST request to update TransactionsRecord : {}", transactionsRecordDTO);
        if (transactionsRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionsRecordDTO result = transactionsRecordService.save(transactionsRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionsRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transactions-records} : get all the transactionsRecords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionsRecords in body.
     */
    @GetMapping("/transactions-records")
    public ResponseEntity<List<TransactionsRecordDTO>> getAllTransactionsRecords(Pageable pageable) {
        log.debug("REST request to get a page of TransactionsRecords");
        Page<TransactionsRecordDTO> page = transactionsRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transactions-records/:id} : get the "id" transactionsRecord.
     *
     * @param id the id of the transactionsRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionsRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactions-records/{id}")
    public ResponseEntity<TransactionsRecordDTO> getTransactionsRecord(@PathVariable Long id) {
        log.debug("REST request to get TransactionsRecord : {}", id);
        Optional<TransactionsRecordDTO> transactionsRecordDTO = transactionsRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionsRecordDTO);
    }

    /**
     * {@code DELETE  /transactions-records/:id} : delete the "id" transactionsRecord.
     *
     * @param id the id of the transactionsRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transactions-records/{id}")
    public ResponseEntity<Void> deleteTransactionsRecord(@PathVariable Long id) {
        log.debug("REST request to delete TransactionsRecord : {}", id);
        transactionsRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/transactions-records?query=:query} : search for the transactionsRecord corresponding
     * to the query.
     *
     * @param query the query of the transactionsRecord search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/transactions-records")
    public ResponseEntity<List<TransactionsRecordDTO>> searchTransactionsRecords(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TransactionsRecords for query {}", query);
        Page<TransactionsRecordDTO> page = transactionsRecordService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
