package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.CompanyProfileService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.CompanyProfileDTO;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.CompanyProfile}.
 */
@RestController
@RequestMapping("/api")
public class CompanyProfileResource {

    private final Logger log = LoggerFactory.getLogger(CompanyProfileResource.class);

    private static final String ENTITY_NAME = "companyProfile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompanyProfileService companyProfileService;

    public CompanyProfileResource(CompanyProfileService companyProfileService) {
        this.companyProfileService = companyProfileService;
    }

    /**
     * {@code POST  /company-profiles} : Create a new companyProfile.
     *
     * @param companyProfileDTO the companyProfileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new companyProfileDTO, or with status {@code 400 (Bad Request)} if the companyProfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/company-profiles")
    public ResponseEntity<CompanyProfileDTO> createCompanyProfile(@RequestBody CompanyProfileDTO companyProfileDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyProfile : {}", companyProfileDTO);
        if (companyProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new companyProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompanyProfileDTO result = companyProfileService.save(companyProfileDTO);
        return ResponseEntity.created(new URI("/api/company-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /company-profiles} : Updates an existing companyProfile.
     *
     * @param companyProfileDTO the companyProfileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companyProfileDTO,
     * or with status {@code 400 (Bad Request)} if the companyProfileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the companyProfileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/company-profiles")
    public ResponseEntity<CompanyProfileDTO> updateCompanyProfile(@RequestBody CompanyProfileDTO companyProfileDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyProfile : {}", companyProfileDTO);
        if (companyProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompanyProfileDTO result = companyProfileService.save(companyProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, companyProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /company-profiles} : get all the companyProfiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of companyProfiles in body.
     */
    @GetMapping("/company-profiles")
    public List<CompanyProfileDTO> getAllCompanyProfiles() {
        log.debug("REST request to get all CompanyProfiles");
        return companyProfileService.findAll();
    }

    /**
     * {@code GET  /company-profiles/:id} : get the "id" companyProfile.
     *
     * @param id the id of the companyProfileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the companyProfileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/company-profiles/{id}")
    public ResponseEntity<CompanyProfileDTO> getCompanyProfile(@PathVariable Long id) {
        log.debug("REST request to get CompanyProfile : {}", id);
        Optional<CompanyProfileDTO> companyProfileDTO = companyProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(companyProfileDTO);
    }

    /**
     * {@code DELETE  /company-profiles/:id} : delete the "id" companyProfile.
     *
     * @param id the id of the companyProfileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/company-profiles/{id}")
    public ResponseEntity<Void> deleteCompanyProfile(@PathVariable Long id) {
        log.debug("REST request to delete CompanyProfile : {}", id);
        companyProfileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/company-profiles?query=:query} : search for the companyProfile corresponding
     * to the query.
     *
     * @param query the query of the companyProfile search.
     * @return the result of the search.
     */
    @GetMapping("/_search/company-profiles")
    public List<CompanyProfileDTO> searchCompanyProfiles(@RequestParam String query) {
        log.debug("REST request to search CompanyProfiles for query {}", query);
        return companyProfileService.search(query);
    }
}
