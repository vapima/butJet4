package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll(Long idUser, Pageable pageable);

    AccountDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    AccountDto addAccount(AccountDto AccountDto, Long idUser);

    AccountDto updateAccount(AccountDto AccountDto, Long id, Long idUser);
}
