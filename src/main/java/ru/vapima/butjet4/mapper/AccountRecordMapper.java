package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordAddDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordEditDto;
import ru.vapima.butjet4.model.db.AccountRecord;

@Mapper(componentModel = "spring")
public interface AccountRecordMapper {
    AccountRecordDto toDto(AccountRecord accountRecord);

    AccountRecord fromDto(AccountRecordDto accountRecordDto);
    AccountRecord fromDto(AccountRecordAddDto accountRecordAddDto);
    AccountRecord fromDto(AccountRecordEditDto accountRecordEditDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(AccountRecordEditDto accountRecordEditDto, @MappingTarget AccountRecord accountRecord);
}
