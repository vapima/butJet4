package com.github.template.mapper;

import com.github.template.dto.AccountDto;
import com.github.template.dto.AccountDto;
import com.github.template.model.db.Account;
import com.github.template.model.db.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);

    Account fromDto(AccountDto accountDto);

    @Mapping(target = "id",ignore = true)
    Account fromDtoFormCreat(AccountDto accountDto);

    @Mapping(target = "id",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToAccount(AccountDto accountDto, @MappingTarget Account account);
}
