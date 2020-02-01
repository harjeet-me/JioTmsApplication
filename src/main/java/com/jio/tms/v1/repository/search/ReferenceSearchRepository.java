package com.jio.tms.v1.repository.search;

import com.jio.tms.v1.domain.Reference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Reference} entity.
 */
public interface ReferenceSearchRepository extends ElasticsearchRepository<Reference, Long> {
}
