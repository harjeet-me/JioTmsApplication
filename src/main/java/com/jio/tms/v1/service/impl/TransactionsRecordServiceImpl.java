package com.jio.tms.v1.service.impl;

import com.jio.tms.v1.service.TransactionsRecordService;
import com.jio.tms.v1.domain.TransactionsRecord;
import com.jio.tms.v1.repository.TransactionsRecordRepository;
import com.jio.tms.v1.repository.search.TransactionsRecordSearchRepository;
import com.jio.tms.v1.service.dto.TransactionsRecordDTO;
import com.jio.tms.v1.service.mapper.TransactionsRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TransactionsRecord}.
 */
@Service
@Transactional
public class TransactionsRecordServiceImpl implements TransactionsRecordService {

    private final Logger log = LoggerFactory.getLogger(TransactionsRecordServiceImpl.class);

    private final TransactionsRecordRepository transactionsRecordRepository;

    private final TransactionsRecordMapper transactionsRecordMapper;

    private final TransactionsRecordSearchRepository transactionsRecordSearchRepository;

    public TransactionsRecordServiceImpl(TransactionsRecordRepository transactionsRecordRepository, TransactionsRecordMapper transactionsRecordMapper, TransactionsRecordSearchRepository transactionsRecordSearchRepository) {
        this.transactionsRecordRepository = transactionsRecordRepository;
        this.transactionsRecordMapper = transactionsRecordMapper;
        this.transactionsRecordSearchRepository = transactionsRecordSearchRepository;
    }

    /**
     * Save a transactionsRecord.
     *
     * @param transactionsRecordDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransactionsRecordDTO save(TransactionsRecordDTO transactionsRecordDTO) {
        log.debug("Request to save TransactionsRecord : {}", transactionsRecordDTO);
        TransactionsRecord transactionsRecord = transactionsRecordMapper.toEntity(transactionsRecordDTO);
        transactionsRecord = transactionsRecordRepository.save(transactionsRecord);
        TransactionsRecordDTO result = transactionsRecordMapper.toDto(transactionsRecord);
        transactionsRecordSearchRepository.save(transactionsRecord);
        return result;
    }

    /**
     * Get all the transactionsRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransactionsRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionsRecords");
        return transactionsRecordRepository.findAll(pageable)
            .map(transactionsRecordMapper::toDto);
    }

    /**
     * Get one transactionsRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionsRecordDTO> findOne(Long id) {
        log.debug("Request to get TransactionsRecord : {}", id);
        return transactionsRecordRepository.findById(id)
            .map(transactionsRecordMapper::toDto);
    }

    /**
     * Delete the transactionsRecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionsRecord : {}", id);
        transactionsRecordRepository.deleteById(id);
        transactionsRecordSearchRepository.deleteById(id);
    }

    /**
     * Search for the transactionsRecord corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransactionsRecordDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TransactionsRecords for query {}", query);
        return transactionsRecordSearchRepository.search(queryStringQuery(query), pageable)
            .map(transactionsRecordMapper::toDto);
    }
}
