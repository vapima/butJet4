package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.AccountDto;
import ru.vapima.butjet4.model.db.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);

    Account fromDto(AccountDto accountDto);

    @Mapping(target = "id", ignore = true)
    Account fromDtoFormCreat(AccountDto accountDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToAccount(AccountDto accountDto, @MappingTarget Account account);
}
