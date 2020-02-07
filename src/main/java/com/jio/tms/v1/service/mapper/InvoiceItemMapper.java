package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.InvoiceItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceItem} and its DTO {@link InvoiceItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvoiceMapper.class})
public interface InvoiceItemMapper extends EntityMapper<InvoiceItemDTO, InvoiceItem> {

    @Mapping(source = "invoice.id", target = "invoiceId")
    InvoiceItemDTO toDto(InvoiceItem invoiceItem);

    @Mapping(source = "invoiceId", target = "invoice")
    InvoiceItem toEntity(InvoiceItemDTO invoiceItemDTO);

    default InvoiceItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(id);
        return invoiceItem;
    }
}
