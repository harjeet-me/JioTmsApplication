package com.jio.tms.v1.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TripSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TripSearchRepositoryMockConfiguration {

    @MockBean
    private TripSearchRepository mockTripSearchRepository;

}
