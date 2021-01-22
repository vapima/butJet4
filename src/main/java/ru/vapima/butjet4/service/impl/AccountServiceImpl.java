package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.account.AccountAddDto;
import ru.vapima.butjet4.dto.account.AccountDto;
import ru.vapima.butjet4.dto.account.AccountEditDto;
import ru.vapima.butjet4.mapper.AccountMapper;
import ru.vapima.butjet4.model.db.Account;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.repository.AccountRecordRepository;
import ru.vapima.butjet4.repository.AccountRepository;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountRecordRepository accountRecordRepository;
    private final AccountMapper mapper;


    @Override
    public List<AccountDto> getAll(Long idUser, Pageable pageable) {
        List<Account> Accounts = accountRepository.findAllByUserId(idUser, pageable);
        return Accounts
                .stream()
                .filter(f -> f.getUser().getId().equals(idUser))
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id, Long idUser) {
        Account Account = accountRepository.getOne(id);
        if (!Account.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your Account.");
        }
        return mapper.toDto(Account);
    }

    @Override
    public void deleteById(Long id, Long idUser) {
        Account Account = accountRepository.getOne(id);
        if (!Account.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your Account.");
        }

        //TODO Как реализовать? Сейчас сам сделал, но может лучше каскад? Что бы не инжектить акк рек репо ради этого?
        accountRecordRepository.deleteAccountRecordByAccountId(id);
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto addAccount(AccountAddDto accountAddDto, Long idUser) {
        Account account = mapper.fromDto(accountAddDto);
        User user = userRepository.getOne(idUser);
        account.setUser(user);
        return mapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto updateAccount(AccountEditDto accountEditDto, Long id, Long idUser) {
        User user = userRepository.getOne(idUser);
        Account account = accountRepository.getOne(id);
        if (!account.getUser().equals(user)) {
            throw new IllegalArgumentException("That's not your account.");
        }
        mapper.patchFromEditDto(accountEditDto, account);
        account.setUser(user);
        account.setId(id);
        return mapper.toDto(accountRepository.save(account));
    }
}
