package com.github.template.controller;

import com.github.template.dto.AccountDto;
import com.github.template.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users/{id_user}/accounts")
@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto save(@RequestBody AccountDto accountDTO, @PathVariable("id_user") Long idUser) {
        return accountService.addAccount(accountDTO, idUser);
    }


    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        return accountService.getById(id, idUser);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        accountService.deleteById(id, idUser);
    }


    @GetMapping
    public List<AccountDto> list(@PageableDefault(value = 10,page = 0) Pageable pageable, @PathVariable("id_user") Long idUser) {
        return accountService.getAll(idUser, pageable);
    }


    @PatchMapping("/{id}")
    public AccountDto update(@RequestBody AccountDto accountDTO, @PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        return accountService.updateAccount(accountDTO, id, idUser);
    }
}
