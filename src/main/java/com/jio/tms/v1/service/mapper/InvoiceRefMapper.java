package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.InvoiceRefDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceRef} and its DTO {@link InvoiceRefDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvoiceRefMapper extends EntityMapper<InvoiceRefDTO, InvoiceRef> {



    default InvoiceRef fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoiceRef invoiceRef = new InvoiceRef();
        invoiceRef.setId(id);
        return invoiceRef;
    }
}
