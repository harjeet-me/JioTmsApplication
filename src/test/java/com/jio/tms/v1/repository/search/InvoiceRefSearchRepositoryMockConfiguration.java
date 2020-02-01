package com.jio.tms.v1.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link InvoiceRefSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class InvoiceRefSearchRepositoryMockConfiguration {

    @MockBean
    private InvoiceRefSearchRepository mockInvoiceRefSearchRepository;

}
