package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.account.AccountAddDto;
import ru.vapima.butjet4.dto.account.AccountDto;
import ru.vapima.butjet4.dto.account.AccountEditDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll(Long idUser, Pageable pageable);

    AccountDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    AccountDto addAccount(AccountAddDto AccountAddDto, Long idUser);

    AccountDto updateAccount(AccountEditDto AccountEditDto, Long id, Long idUser);
}
