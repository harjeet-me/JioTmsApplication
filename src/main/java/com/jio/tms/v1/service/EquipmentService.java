package com.jio.tms.v1.service;

import com.jio.tms.v1.service.dto.EquipmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.jio.tms.v1.domain.Equipment}.
 */
public interface EquipmentService {

    /**
     * Save a equipment.
     *
     * @param equipmentDTO the entity to save.
     * @return the persisted entity.
     */
    EquipmentDTO save(EquipmentDTO equipmentDTO);

    /**
     * Get all the equipment.
     *
     * @return the list of entities.
     */
    List<EquipmentDTO> findAll();

    /**
     * Get the "id" equipment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipmentDTO> findOne(Long id);

    /**
     * Delete the "id" equipment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the equipment corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<EquipmentDTO> search(String query);
}
