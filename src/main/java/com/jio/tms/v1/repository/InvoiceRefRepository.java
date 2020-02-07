package com.jio.tms.v1.repository;

import com.jio.tms.v1.domain.InvoiceRef;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoiceRef entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceRefRepository extends JpaRepository<InvoiceRef, Long> {

}
