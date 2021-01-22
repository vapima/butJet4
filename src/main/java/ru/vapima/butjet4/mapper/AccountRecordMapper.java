package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.AccountRecordDto;
import ru.vapima.butjet4.model.db.AccountRecord;

@Mapper(componentModel = "spring")
public interface AccountRecordMapper {
    AccountRecordDto toDto(AccountRecord accountRecord);

    AccountRecord fromDto(AccountRecordDto accountRecordDto);

    @Mapping(target = "id", ignore = true)
    AccountRecord fromDtoFormCreat(AccountRecordDto accountRecordDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToAccountRecord(AccountRecordDto accountRecordDto, @MappingTarget AccountRecord accountRecord);
}
