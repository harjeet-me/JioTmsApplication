package com.jio.tms.v1.web.rest;

import com.jio.tms.v1.JioTmsApplicationApp;
import com.jio.tms.v1.domain.ProductItem;
import com.jio.tms.v1.repository.ProductItemRepository;
import com.jio.tms.v1.repository.search.ProductItemSearchRepository;
import com.jio.tms.v1.service.ProductItemService;
import com.jio.tms.v1.service.dto.ProductItemDTO;
import com.jio.tms.v1.service.mapper.ProductItemMapper;
import com.jio.tms.v1.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.jio.tms.v1.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductItemResource} REST controller.
 */
@SpringBootTest(classes = JioTmsApplicationApp.class)
public class ProductItemResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductItemMapper productItemMapper;

    @Autowired
    private ProductItemService productItemService;

    /**
     * This repository is mocked in the com.jio.tms.v1.repository.search test package.
     *
     * @see com.jio.tms.v1.repository.search.ProductItemSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProductItemSearchRepository mockProductItemSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProductItemMockMvc;

    private ProductItem productItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductItemResource productItemResource = new ProductItemResource(productItemService);
        this.restProductItemMockMvc = MockMvcBuilders.standaloneSetup(productItemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductItem createEntity(EntityManager em) {
        ProductItem productItem = new ProductItem()
            .itemName(DEFAULT_ITEM_NAME)
            .description(DEFAULT_DESCRIPTION)
            .qty(DEFAULT_QTY)
            .price(DEFAULT_PRICE)
            .discount(DEFAULT_DISCOUNT);
        return productItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductItem createUpdatedEntity(EntityManager em) {
        ProductItem productItem = new ProductItem()
            .itemName(UPDATED_ITEM_NAME)
            .description(UPDATED_DESCRIPTION)
            .qty(UPDATED_QTY)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT);
        return productItem;
    }

    @BeforeEach
    public void initTest() {
        productItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductItem() throws Exception {
        int databaseSizeBeforeCreate = productItemRepository.findAll().size();

        // Create the ProductItem
        ProductItemDTO productItemDTO = productItemMapper.toDto(productItem);
        restProductItemMockMvc.perform(post("/api/product-items")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductItem in the database
        List<ProductItem> productItemList = productItemRepository.findAll();
        assertThat(productItemList).hasSize(databaseSizeBeforeCreate + 1);
        ProductItem testProductItem = productItemList.get(productItemList.size() - 1);
        assertThat(testProductItem.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testProductItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductItem.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testProductItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProductItem.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);

        // Validate the ProductItem in Elasticsearch
        verify(mockProductItemSearchRepository, times(1)).save(testProductItem);
    }

    @Test
    @Transactional
    public void createProductItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productItemRepository.findAll().size();

        // Create the ProductItem with an existing ID
        productItem.setId(1L);
        ProductItemDTO productItemDTO = productItemMapper.toDto(productItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductItemMockMvc.perform(post("/api/product-items")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductItem in the database
        List<ProductItem> productItemList = productItemRepository.findAll();
        assertThat(productItemList).hasSize(databaseSizeBeforeCreate);

        // Validate the ProductItem in Elasticsearch
        verify(mockProductItemSearchRepository, times(0)).save(productItem);
    }


    @Test
    @Transactional
    public void getAllProductItems() throws Exception {
        // Initialize the database
        productItemRepository.saveAndFlush(productItem);

        // Get all the productItemList
        restProductItemMockMvc.perform(get("/api/product-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getProductItem() throws Exception {
        // Initialize the database
        productItemRepository.saveAndFlush(productItem);

        // Get the productItem
        restProductItemMockMvc.perform(get("/api/product-items/{id}", productItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productItem.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProductItem() throws Exception {
        // Get the productItem
        restProductItemMockMvc.perform(get("/api/product-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductItem() throws Exception {
        // Initialize the database
        productItemRepository.saveAndFlush(productItem);

        int databaseSizeBeforeUpdate = productItemRepository.findAll().size();

        // Update the productItem
        ProductItem updatedProductItem = productItemRepository.findById(productItem.getId()).get();
        // Disconnect from session so that the updates on updatedProductItem are not directly saved in db
        em.detach(updatedProductItem);
        updatedProductItem
            .itemName(UPDATED_ITEM_NAME)
            .description(UPDATED_DESCRIPTION)
            .qty(UPDATED_QTY)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT);
        ProductItemDTO productItemDTO = productItemMapper.toDto(updatedProductItem);

        restProductItemMockMvc.perform(put("/api/product-items")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productItemDTO)))
            .andExpect(status().isOk());

        // Validate the ProductItem in the database
        List<ProductItem> productItemList = productItemRepository.findAll();
        assertThat(productItemList).hasSize(databaseSizeBeforeUpdate);
        ProductItem testProductItem = productItemList.get(productItemList.size() - 1);
        assertThat(testProductItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testProductItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductItem.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testProductItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProductItem.getDiscount()).isEqualTo(UPDATED_DISCOUNT);

        // Validate the ProductItem in Elasticsearch
        verify(mockProductItemSearchRepository, times(1)).save(testProductItem);
    }

    @Test
    @Transactional
    public void updateNonExistingProductItem() throws Exception {
        int databaseSizeBeforeUpdate = productItemRepository.findAll().size();

        // Create the ProductItem
        ProductItemDTO productItemDTO = productItemMapper.toDto(productItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductItemMockMvc.perform(put("/api/product-items")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductItem in the database
        List<ProductItem> productItemList = productItemRepository.findAll();
        assertThat(productItemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ProductItem in Elasticsearch
        verify(mockProductItemSearchRepository, times(0)).save(productItem);
    }

    @Test
    @Transactional
    public void deleteProductItem() throws Exception {
        // Initialize the database
        productItemRepository.saveAndFlush(productItem);

        int databaseSizeBeforeDelete = productItemRepository.findAll().size();

        // Delete the productItem
        restProductItemMockMvc.perform(delete("/api/product-items/{id}", productItem.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductItem> productItemList = productItemRepository.findAll();
        assertThat(productItemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ProductItem in Elasticsearch
        verify(mockProductItemSearchRepository, times(1)).deleteById(productItem.getId());
    }

    @Test
    @Transactional
    public void searchProductItem() throws Exception {
        // Initialize the database
        productItemRepository.saveAndFlush(productItem);
        when(mockProductItemSearchRepository.search(queryStringQuery("id:" + productItem.getId())))
            .thenReturn(Collections.singletonList(productItem));
        // Search the productItem
        restProductItemMockMvc.perform(get("/api/_search/product-items?query=id:" + productItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())));
    }
}
