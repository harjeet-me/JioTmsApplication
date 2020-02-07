package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {


    @Mapping(target = "loadOrders", ignore = true)
    @Mapping(target = "removeLoadOrder", ignore = true)
    @Mapping(target = "invoices", ignore = true)
    @Mapping(target = "removeInvoice", ignore = true)
    @Mapping(target = "morecontacts", ignore = true)
    @Mapping(target = "removeMorecontact", ignore = true)
    @Mapping(target = "transactionsRecords", ignore = true)
    @Mapping(target = "removeTransactionsRecord", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
