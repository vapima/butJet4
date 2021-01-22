package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordAddDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordEditDto;

import java.util.List;

public interface AccountRecordService {
    List<AccountRecordDto> getAll(Long idUser, Long idAccount, Pageable pageable);

    AccountRecordDto getById(Long id, Long idUser, Long idAccount);

    void deleteById(Long id, Long idUser, Long idAccount);

    AccountRecordDto addAccountRecord(AccountRecordAddDto accountRecordAddDto, Long idUser, Long idAccount);

    AccountRecordDto updateAccountRecord(AccountRecordEditDto accountRecordEditDto, Long id, Long idUser, Long idAccount);
}
