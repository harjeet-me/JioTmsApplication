package com.jio.tms.v1.repository.search;

import com.jio.tms.v1.domain.Files;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Files} entity.
 */
public interface FilesSearchRepository extends ElasticsearchRepository<Files, Long> {
}
