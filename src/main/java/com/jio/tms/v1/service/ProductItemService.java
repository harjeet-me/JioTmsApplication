package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.ProductItemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.ProductItem}.
 */
public interface ProductItemService {

    /**
     * Save a productItem.
     *
     * @param productItemDTO the entity to save.
     * @return the persisted entity.
     */
    ProductItemDTO save(ProductItemDTO productItemDTO);

    /**
     * Get all the productItems.
     *
     * @return the list of entities.
     */
    List<ProductItemDTO> findAll();


    /**
     * Get the "id" productItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductItemDTO> findOne(Long id);

    /**
     * Delete the "id" productItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the productItem corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<ProductItemDTO> search(String query);
}
