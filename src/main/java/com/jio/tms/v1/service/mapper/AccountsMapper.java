package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.AccountsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Accounts} and its DTO {@link AccountsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface AccountsMapper extends EntityMapper<AccountsDTO, Accounts> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.company", target = "customerCompany")
    AccountsDTO toDto(Accounts accounts);

    @Mapping(source = "customerId", target = "customer")
    @Mapping(target = "transactionsRecords", ignore = true)
    @Mapping(target = "removeTransactionsRecord", ignore = true)
    Accounts toEntity(AccountsDTO accountsDTO);

    default Accounts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Accounts accounts = new Accounts();
        accounts.setId(id);
        return accounts;
    }
}
