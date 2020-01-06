package com.jio.tms.v1.repository.search;

import com.jio.tms.v1.domain.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Location} entity.
 */
public interface LocationSearchRepository extends ElasticsearchRepository<Location, Long> {
}
