package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.account.AccountAddDto;
import ru.vapima.butjet4.dto.account.AccountDto;
import ru.vapima.butjet4.dto.account.AccountEditDto;
import ru.vapima.butjet4.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users/{id_user}/accounts")
@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountDto saveAccount(@RequestBody @Valid AccountAddDto accountAddDto,
                           @PathVariable("id_user") Long idUser,
                           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountService.addAccount(accountAddDto, idUser);
    }


    @GetMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountDto findAccountById(@PathVariable("id") Long id,
                               @PathVariable("id_user") Long idUser,
                               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountService.getById(id, idUser);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public void deleteAccount(@PathVariable("id") Long id,
                       @PathVariable("id_user") Long idUser,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        accountService.deleteById(id, idUser);
    }


    @GetMapping
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public List<AccountDto> listAccounts(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                 @PathVariable("id_user") Long idUser,
                                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountService.getAll(idUser, pageable);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountDto updateAccount(@RequestBody @Valid AccountEditDto accountEditDto,
                             @PathVariable("id") Long id,
                             @PathVariable("id_user") Long idUser,
                             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountService.updateAccount(accountEditDto, id, idUser);
    }
}
