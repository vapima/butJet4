package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.AccountRecordDto;

import java.util.List;

public interface AccountRecordService {
    List<AccountRecordDto> getAll(Long idUser, Long idAccount, Pageable pageable);

    AccountRecordDto getById(Long id, Long idUser, Long idAccount);

    void deleteById(Long id, Long idUser, Long idAccount);

    AccountRecordDto addAccountRecord(AccountRecordDto accountRecordDto, Long idUser, Long idAccount);

    AccountRecordDto updateAccountRecord(AccountRecordDto accountRecordDto, Long id, Long idUser, Long idAccount);
}
