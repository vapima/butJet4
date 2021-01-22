package com.github.template.mapper;

import com.github.template.dto.AccountRecordDto;
import com.github.template.dto.AccountRecordDto;
import com.github.template.model.db.AccountRecord;
import com.github.template.model.db.AccountRecord;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountRecordMapper {
    AccountRecordDto toDto(AccountRecord accountRecord);

    AccountRecord fromDto(AccountRecordDto accountRecordDto);

    @Mapping(target = "id",ignore = true)
    AccountRecord fromDtoFormCreat(AccountRecordDto accountRecordDto);

    @Mapping(target = "id",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToAccountRecord(AccountRecordDto accountRecordDto, @MappingTarget AccountRecord accountRecord);
}
