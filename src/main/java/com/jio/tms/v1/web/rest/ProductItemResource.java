package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.service.ProductItemService;
import com.jio.tms.v1.web.rest.errors.BadRequestAlertException;
import com.jio.tms.v1.service.dto.ProductItemDTO;

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
 * REST controller for managing {@link com.jio.tms.v1.domain.ProductItem}.
 */
@RestController
@RequestMapping("/api")
public class ProductItemResource {

    private final Logger log = LoggerFactory.getLogger(ProductItemResource.class);

    private static final String ENTITY_NAME = "productItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductItemService productItemService;

    public ProductItemResource(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    /**
     * {@code POST  /product-items} : Create a new productItem.
     *
     * @param productItemDTO the productItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productItemDTO, or with status {@code 400 (Bad Request)} if the productItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-items")
    public ResponseEntity<ProductItemDTO> createProductItem(@RequestBody ProductItemDTO productItemDTO) throws URISyntaxException {
        log.debug("REST request to save ProductItem : {}", productItemDTO);
        if (productItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new productItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductItemDTO result = productItemService.save(productItemDTO);
        return ResponseEntity.created(new URI("/api/product-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-items} : Updates an existing productItem.
     *
     * @param productItemDTO the productItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productItemDTO,
     * or with status {@code 400 (Bad Request)} if the productItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-items")
    public ResponseEntity<ProductItemDTO> updateProductItem(@RequestBody ProductItemDTO productItemDTO) throws URISyntaxException {
        log.debug("REST request to update ProductItem : {}", productItemDTO);
        if (productItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductItemDTO result = productItemService.save(productItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-items} : get all the productItems.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productItems in body.
     */
    @GetMapping("/product-items")
    public List<ProductItemDTO> getAllProductItems() {
        log.debug("REST request to get all ProductItems");
        return productItemService.findAll();
    }

    /**
     * {@code GET  /product-items/:id} : get the "id" productItem.
     *
     * @param id the id of the productItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-items/{id}")
    public ResponseEntity<ProductItemDTO> getProductItem(@PathVariable Long id) {
        log.debug("REST request to get ProductItem : {}", id);
        Optional<ProductItemDTO> productItemDTO = productItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productItemDTO);
    }

    /**
     * {@code DELETE  /product-items/:id} : delete the "id" productItem.
     *
     * @param id the id of the productItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-items/{id}")
    public ResponseEntity<Void> deleteProductItem(@PathVariable Long id) {
        log.debug("REST request to delete ProductItem : {}", id);
        productItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/product-items?query=:query} : search for the productItem corresponding
     * to the query.
     *
     * @param query the query of the productItem search.
     * @return the result of the search.
     */
    @GetMapping("/_search/product-items")
    public List<ProductItemDTO> searchProductItems(@RequestParam String query) {
        log.debug("REST request to search ProductItems for query {}", query);
        return productItemService.search(query);
    }
}
