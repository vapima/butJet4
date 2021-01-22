package com.github.template.service;

import com.github.template.dto.AccountRecordDto;
import com.github.template.mapper.AccountRecordMapper;
import com.github.template.model.db.Account;
import com.github.template.model.db.AccountRecord;
import com.github.template.repository.AccountRecordRepository;
import com.github.template.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        List<AccountRecord> accountRecords = accountRecordRepository.findAllByAccountId(idAccount,pageable);
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
    public AccountRecordDto addAccountRecord(AccountRecordDto accountRecordDto, Long idUser, Long idAccount) {
        Account account = accountRepository.getOne(idAccount);
        AccountRecord accountRecord = mapper.fromDtoFormCreat(accountRecordDto);
        accountRecord.setAccount(account);
        if (accountRecord.getDateTime()==null) {
            accountRecord.setDateTime(LocalDateTime.now());
        }
        return mapper.toDto(accountRecordRepository.save(accountRecord));
    }

    @Override
    public AccountRecordDto updateAccountRecord(AccountRecordDto accountRecordDto, Long id, Long idUser, Long idAccount) {
        Account account = accountRepository.getOne(idAccount);
        if (!account.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your accountRecord.");
        }
        AccountRecord accountRecord = accountRecordRepository.getOne(id);
        if (!accountRecord.getAccount().equals(account)) {
            throw new IllegalArgumentException("That's record not from this account.");
        }
        mapper.formEditToAccountRecord(accountRecordDto,accountRecord);
        accountRecord.setId(id);
        return mapper.toDto(accountRecordRepository.save(accountRecord));
    }
}
