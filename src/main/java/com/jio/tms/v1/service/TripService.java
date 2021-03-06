package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.TripDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Trip}.
 */
public interface TripService {

    /**
     * Save a trip.
     *
     * @param tripDTO the entity to save.
     * @return the persisted entity.
     */
    TripDTO save(TripDTO tripDTO);

    /**
     * Get all the trips.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TripDTO> findAll(Pageable pageable);

    /**
     * Get the "id" trip.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TripDTO> findOne(Long id);

    /**
     * Delete the "id" trip.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the trip corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TripDTO> search(String query, Pageable pageable);
}
