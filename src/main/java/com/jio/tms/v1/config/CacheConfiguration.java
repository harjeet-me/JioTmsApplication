package com.jio.tms.v1.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.jio.tms.v1.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.jio.tms.v1.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.jio.tms.v1.domain.User.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Authority.class.getName());
            createCache(cm, com.jio.tms.v1.domain.User.class.getName() + ".authorities");
            createCache(cm, com.jio.tms.v1.domain.CompanyProfile.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Customer.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Customer.class.getName() + ".loadOrders");
            createCache(cm, com.jio.tms.v1.domain.Customer.class.getName() + ".invoices");
            createCache(cm, com.jio.tms.v1.domain.Customer.class.getName() + ".morecontacts");
            createCache(cm, com.jio.tms.v1.domain.Customer.class.getName() + ".transactionsRecords");
            createCache(cm, com.jio.tms.v1.domain.Trip.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Trip.class.getName() + ".invoices");
            createCache(cm, com.jio.tms.v1.domain.Invoice.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Invoice.class.getName() + ".invoiceItems");
            createCache(cm, com.jio.tms.v1.domain.InvoiceItem.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Accounts.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Accounts.class.getName() + ".transactionsRecords");
            createCache(cm, com.jio.tms.v1.domain.TransactionsRecord.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Equipment.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Equipment.class.getName() + ".trips");
            createCache(cm, com.jio.tms.v1.domain.Insurance.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Contact.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Driver.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Driver.class.getName() + ".trips");
            createCache(cm, com.jio.tms.v1.domain.OwnerOperator.class.getName());
            createCache(cm, com.jio.tms.v1.domain.OwnerOperator.class.getName() + ".loadOrders");
            createCache(cm, com.jio.tms.v1.domain.Location.class.getName());
            createCache(cm, com.jio.tms.v1.domain.ProductItem.class.getName());
            createCache(cm, com.jio.tms.v1.domain.InvoiceRef.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Reference.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Email.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Email.class.getName() + ".files");
            createCache(cm, com.jio.tms.v1.domain.Files.class.getName());
            createCache(cm, com.jio.tms.v1.domain.Email.class.getName() + ".fileSystems");
            createCache(cm, com.jio.tms.v1.domain.FileSystem.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
