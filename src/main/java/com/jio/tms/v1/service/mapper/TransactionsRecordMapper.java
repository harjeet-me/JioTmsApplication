package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.TransactionsRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransactionsRecord} and its DTO {@link TransactionsRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, AccountsMapper.class})
public interface TransactionsRecordMapper extends EntityMapper<TransactionsRecordDTO, TransactionsRecord> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.company", target = "customerCompany")
    @Mapping(source = "account.id", target = "accountId")
    TransactionsRecordDTO toDto(TransactionsRecord transactionsRecord);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "accountId", target = "account")
    TransactionsRecord toEntity(TransactionsRecordDTO transactionsRecordDTO);

    default TransactionsRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransactionsRecord transactionsRecord = new TransactionsRecord();
        transactionsRecord.setId(id);
        return transactionsRecord;
    }
}
