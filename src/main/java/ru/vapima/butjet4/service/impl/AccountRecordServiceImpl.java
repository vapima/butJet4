package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordAddDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordEditDto;
import ru.vapima.butjet4.mapper.AccountRecordMapper;
import ru.vapima.butjet4.model.db.Account;
import ru.vapima.butjet4.model.db.AccountRecord;
import ru.vapima.butjet4.repository.AccountRecordRepository;
import ru.vapima.butjet4.repository.AccountRepository;
import ru.vapima.butjet4.service.AccountRecordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountRecordServiceImpl implements AccountRecordService {

    private final AccountRecordRepository accountRecordRepository;
    private final AccountRepository accountRepository;
    private final AccountRecordMapper mapper;


    @Override
    public List<AccountRecordDto> getAll(Long idUser, Long idAccount, Pageable pageable) {
        Account account = accountRepository.findByIdAndUserId(idAccount,idUser);
        List<AccountRecord> accountRecords = accountRecordRepository.findAllByAccountId(account.getId(), pageable);
        return accountRecords
                .stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountRecordDto getById(Long id, Long idUser, Long idAccount) {
        Account account = accountRepository.findByIdAndUserId(idAccount,idUser);
        AccountRecord accountRecord = accountRecordRepository.findByIdAndAccountId(id,account.getId());
        return mapper.toDto(accountRecord);
    }

    @Override
    public void deleteById(Long id, Long idUser, Long idAccount) {
        Account account = accountRepository.findByIdAndUserId(idAccount,idUser);
        AccountRecord accountRecord = accountRecordRepository.findByIdAndAccountId(id,account.getId());
        accountRecordRepository.delete(accountRecord);
    }

    @Override
    public AccountRecordDto addAccountRecord(AccountRecordAddDto accountRecordAddDto, Long idUser, Long idAccount) {
        Account account = accountRepository.findByIdAndUserId(idAccount,idUser);
        AccountRecord accountRecordInput = mapper.fromDto(accountRecordAddDto);
        accountRecordInput.setAccount(account);
        if (accountRecordInput.getDateTime() == null) {
            accountRecordInput.setDateTime(LocalDateTime.now());
        }
        return mapper.toDto(accountRecordRepository.save(accountRecordInput));
    }

    @Override
    public AccountRecordDto updateAccountRecord(AccountRecordEditDto accountRecordEditDto, Long id, Long idUser, Long idAccount) {
        Account account = accountRepository.findByIdAndUserId(idAccount,idUser);
        AccountRecord accountRecord = accountRecordRepository.findByIdAndAccountId(id,account.getId());
        mapper.patchFromEditDto(accountRecordEditDto, accountRecord);
        accountRecord.setId(id);
        return mapper.toDto(accountRecordRepository.save(accountRecord));
    }
}
