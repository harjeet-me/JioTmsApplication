package com.jio.tms.v1.repository.search;

import com.jio.tms.v1.domain.ProductItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ProductItem} entity.
 */
public interface ProductItemSearchRepository extends ElasticsearchRepository<ProductItem, Long> {
}
