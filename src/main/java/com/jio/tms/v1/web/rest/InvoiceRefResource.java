package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.InvoiceRefService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.InvoiceRefDTO;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.InvoiceRef}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceRefResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceRefResource.class);

    private static final String ENTITY_NAME = "invoiceRef";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceRefService invoiceRefService;

    public InvoiceRefResource(InvoiceRefService invoiceRefService) {
        this.invoiceRefService = invoiceRefService;
    }

    /**
     * {@code POST  /invoice-refs} : Create a new invoiceRef.
     *
     * @param invoiceRefDTO the invoiceRefDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceRefDTO, or with status {@code 400 (Bad Request)} if the invoiceRef has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-refs")
    public ResponseEntity<InvoiceRefDTO> createInvoiceRef(@RequestBody InvoiceRefDTO invoiceRefDTO) throws URISyntaxException {
        log.debug("REST request to save InvoiceRef : {}", invoiceRefDTO);
        if (invoiceRefDTO.getId() != null) {
            throw new BadRequestAlertException("A new invoiceRef cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceRefDTO result = invoiceRefService.save(invoiceRefDTO);
        return ResponseEntity.created(new URI("/api/invoice-refs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-refs} : Updates an existing invoiceRef.
     *
     * @param invoiceRefDTO the invoiceRefDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceRefDTO,
     * or with status {@code 400 (Bad Request)} if the invoiceRefDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceRefDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-refs")
    public ResponseEntity<InvoiceRefDTO> updateInvoiceRef(@RequestBody InvoiceRefDTO invoiceRefDTO) throws URISyntaxException {
        log.debug("REST request to update InvoiceRef : {}", invoiceRefDTO);
        if (invoiceRefDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceRefDTO result = invoiceRefService.save(invoiceRefDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceRefDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-refs} : get all the invoiceRefs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceRefs in body.
     */
    @GetMapping("/invoice-refs")
    public List<InvoiceRefDTO> getAllInvoiceRefs() {
        log.debug("REST request to get all InvoiceRefs");
        return invoiceRefService.findAll();
    }

    /**
     * {@code GET  /invoice-refs/:id} : get the "id" invoiceRef.
     *
     * @param id the id of the invoiceRefDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceRefDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-refs/{id}")
    public ResponseEntity<InvoiceRefDTO> getInvoiceRef(@PathVariable Long id) {
        log.debug("REST request to get InvoiceRef : {}", id);
        Optional<InvoiceRefDTO> invoiceRefDTO = invoiceRefService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceRefDTO);
    }

    /**
     * {@code DELETE  /invoice-refs/:id} : delete the "id" invoiceRef.
     *
     * @param id the id of the invoiceRefDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-refs/{id}")
    public ResponseEntity<Void> deleteInvoiceRef(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceRef : {}", id);
        invoiceRefService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invoice-refs?query=:query} : search for the invoiceRef corresponding
     * to the query.
     *
     * @param query the query of the invoiceRef search.
     * @return the result of the search.
     */
    @GetMapping("/_search/invoice-refs")
    public List<InvoiceRefDTO> searchInvoiceRefs(@RequestParam String query) {
        log.debug("REST request to search InvoiceRefs for query {}", query);
        return invoiceRefService.search(query);
    }
}
