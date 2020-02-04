package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.CompanyProfileService;
import com.jio.tms.v1.domain.CompanyProfile;
import com.jio.tms.v1.repository.CompanyProfileRepository;
import com.jio.tms.v1.repository.search.CompanyProfileSearchRepository;
import com.jio.tms.v1.service.dto.CompanyProfileDTO;
import com.jio.tms.v1.service.mapper.CompanyProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CompanyProfile}.
 */
@Service
@Transactional
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final Logger log = LoggerFactory.getLogger(CompanyProfileServiceImpl.class);

    private final CompanyProfileRepository companyProfileRepository;

    private final CompanyProfileMapper companyProfileMapper;

    private final CompanyProfileSearchRepository companyProfileSearchRepository;

    public CompanyProfileServiceImpl(CompanyProfileRepository companyProfileRepository, CompanyProfileMapper companyProfileMapper, CompanyProfileSearchRepository companyProfileSearchRepository) {
        this.companyProfileRepository = companyProfileRepository;
        this.companyProfileMapper = companyProfileMapper;
        this.companyProfileSearchRepository = companyProfileSearchRepository;
    }

    /**
     * Save a companyProfile.
     *
     * @param companyProfileDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyProfileDTO save(CompanyProfileDTO companyProfileDTO) {
        log.debug("Request to save CompanyProfile : {}", companyProfileDTO);
        CompanyProfile companyProfile = companyProfileMapper.toEntity(companyProfileDTO);
        companyProfile = companyProfileRepository.save(companyProfile);
        CompanyProfileDTO result = companyProfileMapper.toDto(companyProfile);
        companyProfileSearchRepository.save(companyProfile);
        return result;
    }

    /**
     * Get all the companyProfiles.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompanyProfileDTO> findAll() {
        log.debug("Request to get all CompanyProfiles");
        return companyProfileRepository.findAll().stream()
            .map(companyProfileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one companyProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyProfileDTO> findOne(Long id) {
        log.debug("Request to get CompanyProfile : {}", id);
        return companyProfileRepository.findById(id)
            .map(companyProfileMapper::toDto);
    }

    /**
     * Delete the companyProfile by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyProfile : {}", id);
        companyProfileRepository.deleteById(id);
        companyProfileSearchRepository.deleteById(id);
    }

    /**
     * Search for the companyProfile corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompanyProfileDTO> search(String query) {
        log.debug("Request to search CompanyProfiles for query {}", query);
        return StreamSupport
            .stream(companyProfileSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(companyProfileMapper::toDto)
            .collect(Collectors.toList());
    }
}
