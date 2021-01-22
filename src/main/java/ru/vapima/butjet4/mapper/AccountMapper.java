package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.account.AccountAddDto;
import ru.vapima.butjet4.dto.account.AccountDto;
import ru.vapima.butjet4.dto.account.AccountEditDto;
import ru.vapima.butjet4.model.db.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);

    Account fromDto(AccountDto accountDto);
    Account fromDto(AccountAddDto accountAddDto);
    Account fromDto(AccountEditDto accountEditDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(AccountEditDto accountEditDto, @MappingTarget Account account);
}
