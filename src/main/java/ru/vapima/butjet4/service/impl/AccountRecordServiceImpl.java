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
        Account account = accountRepository.getOne(idAccount);
        if (!account.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your accountRecord.");
        }
        List<AccountRecord> accountRecords = accountRecordRepository.findAllByAccountId(idAccount, pageable);
        return accountRecords
                .stream()
                .filter(f -> f.getAccount().getUser().getId().equals(idUser))
                .filter(f -> f.getAccount().getId().equals(idAccount))
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountRecordDto getById(Long id, Long idUser, Long idAccount) {
        AccountRecord accountRecord = accountRecordRepository.getOne(id);
        Account account = accountRepository.getOne(idAccount);
        if (!account.getUser().getId().equals(idUser) || !accountRecord.getAccount().equals(account)) {
            throw new IllegalArgumentException("That's not your accountRecord.");
        }
        return mapper.toDto(accountRecord);
    }

    @Override
    public void deleteById(Long id, Long idUser, Long idAccount) {
        AccountRecord accountRecord = accountRecordRepository.getOne(id);
        Account account = accountRepository.getOne(idAccount);
        if (!account.getUser().getId().equals(idUser) || !accountRecord.getAccount().equals(account)) {
            throw new IllegalArgumentException("That's not your accountRecord.");
        }
        accountRecordRepository.deleteById(id);
    }

    @Override
    public AccountRecordDto addAccountRecord(AccountRecordAddDto accountRecordAddDto, Long idUser, Long idAccount) {
        Account account = accountRepository.getOne(idAccount);
        AccountRecord accountRecord = mapper.fromDto(accountRecordAddDto);
        accountRecord.setAccount(account);
        if (accountRecord.getDateTime() == null) {
            accountRecord.setDateTime(LocalDateTime.now());
        }
        return mapper.toDto(accountRecordRepository.save(accountRecord));
    }

    @Override
    public AccountRecordDto updateAccountRecord(AccountRecordEditDto accountRecordEditDto, Long id, Long idUser, Long idAccount) {
        Account account = accountRepository.getOne(idAccount);
        if (!account.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your accountRecord.");
        }
        AccountRecord accountRecord = accountRecordRepository.getOne(id);
        if (!accountRecord.getAccount().equals(account)) {
            throw new IllegalArgumentException("That's record not from this account.");
        }
        mapper.patchFromEditDto(accountRecordEditDto, accountRecord);
        accountRecord.setId(id);
        return mapper.toDto(accountRecordRepository.save(accountRecord));
    }
}
