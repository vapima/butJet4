package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.account.AccountAddDto;
import ru.vapima.butjet4.dto.account.AccountDto;
import ru.vapima.butjet4.dto.account.AccountEditDto;
import ru.vapima.butjet4.service.AccountService;

import java.util.List;

@RequestMapping("/users/{id_user}/accounts")
@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto save(@RequestBody AccountAddDto accountAddDto, @PathVariable("id_user") Long idUser) {
        return accountService.addAccount(accountAddDto, idUser);
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
    public List<AccountDto> list(@PageableDefault(value = 10, page = 0) Pageable pageable, @PathVariable("id_user") Long idUser) {
        return accountService.getAll(idUser, pageable);
    }


    @PatchMapping("/{id}")
    public AccountDto update(@RequestBody AccountEditDto accountEditDto, @PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        return accountService.updateAccount(accountEditDto, id, idUser);
    }
}
