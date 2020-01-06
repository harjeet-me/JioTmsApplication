package com.jio.tms.v1.repository.search;

import com.jio.tms.v1.domain.OwnerOperator;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link OwnerOperator} entity.
 */
public interface OwnerOperatorSearchRepository extends ElasticsearchRepository<OwnerOperator, Long> {
}
