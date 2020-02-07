package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.EmailDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Email} and its DTO {@link EmailDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmailMapper extends EntityMapper<EmailDTO, Email> {


    @Mapping(target = "fileSystems", ignore = true)
    @Mapping(target = "removeFileSystem", ignore = true)
    Email toEntity(EmailDTO emailDTO);

    default Email fromId(Long id) {
        if (id == null) {
            return null;
        }
        Email email = new Email();
        email.setId(id);
        return email;
    }
}
