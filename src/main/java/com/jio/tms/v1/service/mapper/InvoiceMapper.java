package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.InvoiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Invoice} and its DTO {@link InvoiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class, CustomerMapper.class, TripMapper.class})
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {

    @Mapping(source = "reference1.id", target = "reference1Id")
    @Mapping(source = "reference1.reference", target = "reference1Reference")
    @Mapping(source = "reference2.id", target = "reference2Id")
    @Mapping(source = "reference2.reference", target = "reference2Reference")
    @Mapping(source = "reference3.id", target = "reference3Id")
    @Mapping(source = "reference3.reference", target = "reference3Reference")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.company", target = "customerCompany")
    @Mapping(source = "trip.id", target = "tripId")
    InvoiceDTO toDto(Invoice invoice);

    @Mapping(source = "reference1Id", target = "reference1")
    @Mapping(source = "reference2Id", target = "reference2")
    @Mapping(source = "reference3Id", target = "reference3")
    @Mapping(target = "invoiceItems", ignore = true)
    @Mapping(target = "removeInvoiceItem", ignore = true)
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "tripId", target = "trip")
    Invoice toEntity(InvoiceDTO invoiceDTO);

    default Invoice fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invoice invoice = new Invoice();
        invoice.setId(id);
        return invoice;
    }
}
