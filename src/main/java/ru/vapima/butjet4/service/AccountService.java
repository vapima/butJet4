package com.github.template.service;

import com.github.template.dto.AccountDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll(Long idUser, Pageable pageable);

    AccountDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    AccountDto addAccount(AccountDto AccountDto, Long idUser);

    AccountDto updateAccount(AccountDto AccountDto, Long id, Long idUser);
}
