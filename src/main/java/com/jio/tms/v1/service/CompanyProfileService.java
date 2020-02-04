package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.CompanyProfileDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.CompanyProfile}.
 */
public interface CompanyProfileService {

    /**
     * Save a companyProfile.
     *
     * @param companyProfileDTO the entity to save.
     * @return the persisted entity.
     */
    CompanyProfileDTO save(CompanyProfileDTO companyProfileDTO);

    /**
     * Get all the companyProfiles.
     *
     * @return the list of entities.
     */
    List<CompanyProfileDTO> findAll();


    /**
     * Get the "id" companyProfile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompanyProfileDTO> findOne(Long id);

    /**
     * Delete the "id" companyProfile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the companyProfile corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<CompanyProfileDTO> search(String query);
}
