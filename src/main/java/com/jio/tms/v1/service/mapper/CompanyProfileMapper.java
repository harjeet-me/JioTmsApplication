package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.CompanyProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompanyProfile} and its DTO {@link CompanyProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompanyProfileMapper extends EntityMapper<CompanyProfileDTO, CompanyProfile> {



    default CompanyProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setId(id);
        return companyProfile;
    }
}
