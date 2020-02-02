package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.domain.Files;
import com.jio.tms.v1.service.FilesService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.Files}.
 */
@RestController
@RequestMapping("/api")
public class FilesResource {

    private final Logger log = LoggerFactory.getLogger(FilesResource.class);

    private static final String ENTITY_NAME = "files";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FilesService filesService;

    public FilesResource(FilesService filesService) {
        this.filesService = filesService;
    }

    /**
     * {@code POST  /files} : Create a new files.
     *
     * @param files the files to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new files, or with status {@code 400 (Bad Request)} if the files has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/files")
    public ResponseEntity<Files> createFiles(@RequestBody Files files) throws URISyntaxException {
        log.debug("REST request to save Files : {}", files);
        if (files.getId() != null) {
            throw new BadRequestAlertException("A new files cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Files result = filesService.save(files);
        return ResponseEntity.created(new URI("/api/files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /files} : Updates an existing files.
     *
     * @param files the files to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated files,
     * or with status {@code 400 (Bad Request)} if the files is not valid,
     * or with status {@code 500 (Internal Server Error)} if the files couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/files")
    public ResponseEntity<Files> updateFiles(@RequestBody Files files) throws URISyntaxException {
        log.debug("REST request to update Files : {}", files);
        if (files.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Files result = filesService.save(files);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, files.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /files} : get all the files.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of files in body.
     */
    @GetMapping("/files")
    public List<Files> getAllFiles() {
        log.debug("REST request to get all Files");
        return filesService.findAll();
    }

    /**
     * {@code GET  /files/:id} : get the "id" files.
     *
     * @param id the id of the files to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the files, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/files/{id}")
    public ResponseEntity<Files> getFiles(@PathVariable Long id) {
        log.debug("REST request to get Files : {}", id);
        Optional<Files> files = filesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(files);
    }

    /**
     * {@code DELETE  /files/:id} : delete the "id" files.
     *
     * @param id the id of the files to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/files/{id}")
    public ResponseEntity<Void> deleteFiles(@PathVariable Long id) {
        log.debug("REST request to delete Files : {}", id);
        filesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/files?query=:query} : search for the files corresponding
     * to the query.
     *
     * @param query the query of the files search.
     * @return the result of the search.
     */
    @GetMapping("/_search/files")
    public List<Files> searchFiles(@RequestParam String query) {
        log.debug("REST request to search Files for query {}", query);
        return filesService.search(query);
    }
}
