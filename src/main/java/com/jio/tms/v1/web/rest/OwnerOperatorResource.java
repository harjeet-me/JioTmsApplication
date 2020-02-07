package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.OwnerOperatorService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.OwnerOperatorDTO;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.OwnerOperator}.
 */
@RestController
@RequestMapping("/api")
public class OwnerOperatorResource {

    private final Logger log = LoggerFactory.getLogger(OwnerOperatorResource.class);

    private static final String ENTITY_NAME = "ownerOperator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OwnerOperatorService ownerOperatorService;

    public OwnerOperatorResource(OwnerOperatorService ownerOperatorService) {
        this.ownerOperatorService = ownerOperatorService;
    }

    /**
     * {@code POST  /owner-operators} : Create a new ownerOperator.
     *
     * @param ownerOperatorDTO the ownerOperatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ownerOperatorDTO, or with status {@code 400 (Bad Request)} if the ownerOperator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/owner-operators")
    public ResponseEntity<OwnerOperatorDTO> createOwnerOperator(@RequestBody OwnerOperatorDTO ownerOperatorDTO) throws URISyntaxException {
        log.debug("REST request to save OwnerOperator : {}", ownerOperatorDTO);
        if (ownerOperatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new ownerOperator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerOperatorDTO result = ownerOperatorService.save(ownerOperatorDTO);
        return ResponseEntity.created(new URI("/api/owner-operators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /owner-operators} : Updates an existing ownerOperator.
     *
     * @param ownerOperatorDTO the ownerOperatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ownerOperatorDTO,
     * or with status {@code 400 (Bad Request)} if the ownerOperatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ownerOperatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/owner-operators")
    public ResponseEntity<OwnerOperatorDTO> updateOwnerOperator(@RequestBody OwnerOperatorDTO ownerOperatorDTO) throws URISyntaxException {
        log.debug("REST request to update OwnerOperator : {}", ownerOperatorDTO);
        if (ownerOperatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OwnerOperatorDTO result = ownerOperatorService.save(ownerOperatorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ownerOperatorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /owner-operators} : get all the ownerOperators.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ownerOperators in body.
     */
    @GetMapping("/owner-operators")
    public List<OwnerOperatorDTO> getAllOwnerOperators() {
        log.debug("REST request to get all OwnerOperators");
        return ownerOperatorService.findAll();
    }

    /**
     * {@code GET  /owner-operators/:id} : get the "id" ownerOperator.
     *
     * @param id the id of the ownerOperatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ownerOperatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/owner-operators/{id}")
    public ResponseEntity<OwnerOperatorDTO> getOwnerOperator(@PathVariable Long id) {
        log.debug("REST request to get OwnerOperator : {}", id);
        Optional<OwnerOperatorDTO> ownerOperatorDTO = ownerOperatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ownerOperatorDTO);
    }

    /**
     * {@code DELETE  /owner-operators/:id} : delete the "id" ownerOperator.
     *
     * @param id the id of the ownerOperatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/owner-operators/{id}")
    public ResponseEntity<Void> deleteOwnerOperator(@PathVariable Long id) {
        log.debug("REST request to delete OwnerOperator : {}", id);
        ownerOperatorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/owner-operators?query=:query} : search for the ownerOperator corresponding
     * to the query.
     *
     * @param query the query of the ownerOperator search.
     * @return the result of the search.
     */
    @GetMapping("/_search/owner-operators")
    public List<OwnerOperatorDTO> searchOwnerOperators(@RequestParam String query) {
        log.debug("REST request to search OwnerOperators for query {}", query);
        return ownerOperatorService.search(query);
    }
}
