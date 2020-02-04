package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.FileSystemService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.FileSystemDTO;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.FileSystem}.
 */
@RestController
@RequestMapping("/api")
public class FileSystemResource {

    private final Logger log = LoggerFactory.getLogger(FileSystemResource.class);

    private static final String ENTITY_NAME = "fileSystem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileSystemService fileSystemService;

    public FileSystemResource(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    /**
     * {@code POST  /file-systems} : Create a new fileSystem.
     *
     * @param fileSystemDTO the fileSystemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileSystemDTO, or with status {@code 400 (Bad Request)} if the fileSystem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-systems")
    public ResponseEntity<FileSystemDTO> createFileSystem(@RequestBody FileSystemDTO fileSystemDTO) throws URISyntaxException {
        log.debug("REST request to save FileSystem : {}", fileSystemDTO);
        if (fileSystemDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileSystem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileSystemDTO result = fileSystemService.save(fileSystemDTO);
        return ResponseEntity.created(new URI("/api/file-systems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-systems} : Updates an existing fileSystem.
     *
     * @param fileSystemDTO the fileSystemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileSystemDTO,
     * or with status {@code 400 (Bad Request)} if the fileSystemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileSystemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-systems")
    public ResponseEntity<FileSystemDTO> updateFileSystem(@RequestBody FileSystemDTO fileSystemDTO) throws URISyntaxException {
        log.debug("REST request to update FileSystem : {}", fileSystemDTO);
        if (fileSystemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileSystemDTO result = fileSystemService.save(fileSystemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileSystemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-systems} : get all the fileSystems.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileSystems in body.
     */
    @GetMapping("/file-systems")
    public ResponseEntity<List<FileSystemDTO>> getAllFileSystems(Pageable pageable) {
        log.debug("REST request to get a page of FileSystems");
        Page<FileSystemDTO> page = fileSystemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-systems/:id} : get the "id" fileSystem.
     *
     * @param id the id of the fileSystemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileSystemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-systems/{id}")
    public ResponseEntity<FileSystemDTO> getFileSystem(@PathVariable Long id) {
        log.debug("REST request to get FileSystem : {}", id);
        Optional<FileSystemDTO> fileSystemDTO = fileSystemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileSystemDTO);
    }

    /**
     * {@code DELETE  /file-systems/:id} : delete the "id" fileSystem.
     *
     * @param id the id of the fileSystemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-systems/{id}")
    public ResponseEntity<Void> deleteFileSystem(@PathVariable Long id) {
        log.debug("REST request to delete FileSystem : {}", id);
        fileSystemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/file-systems?query=:query} : search for the fileSystem corresponding
     * to the query.
     *
     * @param query the query of the fileSystem search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/file-systems")
    public ResponseEntity<List<FileSystemDTO>> searchFileSystems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FileSystems for query {}", query);
        Page<FileSystemDTO> page = fileSystemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
