package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.ProductItemService;
import com.jio.tms.v1.domain.ProductItem;
import com.jio.tms.v1.repository.ProductItemRepository;
import com.jio.tms.v1.repository.search.ProductItemSearchRepository;
import com.jio.tms.v1.service.dto.ProductItemDTO;
import com.jio.tms.v1.service.mapper.ProductItemMapper;
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
 * Service Implementation for managing {@link ProductItem}.
 */
@Service
@Transactional
public class ProductItemServiceImpl implements ProductItemService {

    private final Logger log = LoggerFactory.getLogger(ProductItemServiceImpl.class);

    private final ProductItemRepository productItemRepository;

    private final ProductItemMapper productItemMapper;

    private final ProductItemSearchRepository productItemSearchRepository;

    public ProductItemServiceImpl(ProductItemRepository productItemRepository, ProductItemMapper productItemMapper, ProductItemSearchRepository productItemSearchRepository) {
        this.productItemRepository = productItemRepository;
        this.productItemMapper = productItemMapper;
        this.productItemSearchRepository = productItemSearchRepository;
    }

    /**
     * Save a productItem.
     *
     * @param productItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductItemDTO save(ProductItemDTO productItemDTO) {
        log.debug("Request to save ProductItem : {}", productItemDTO);
        ProductItem productItem = productItemMapper.toEntity(productItemDTO);
        productItem = productItemRepository.save(productItem);
        ProductItemDTO result = productItemMapper.toDto(productItem);
        productItemSearchRepository.save(productItem);
        return result;
    }

    /**
     * Get all the productItems.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductItemDTO> findAll() {
        log.debug("Request to get all ProductItems");
        return productItemRepository.findAll().stream()
            .map(productItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one productItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductItemDTO> findOne(Long id) {
        log.debug("Request to get ProductItem : {}", id);
        return productItemRepository.findById(id)
            .map(productItemMapper::toDto);
    }

    /**
     * Delete the productItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductItem : {}", id);
        productItemRepository.deleteById(id);
        productItemSearchRepository.deleteById(id);
    }

    /**
     * Search for the productItem corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductItemDTO> search(String query) {
        log.debug("Request to search ProductItems for query {}", query);
        return StreamSupport
            .stream(productItemSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(productItemMapper::toDto)
            .collect(Collectors.toList());
    }
}
