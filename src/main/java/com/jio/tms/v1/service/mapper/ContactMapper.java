package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.company", target = "customerCompany")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "customerId", target = "customer")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
