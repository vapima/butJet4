package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordAddDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordDto;
import ru.vapima.butjet4.dto.accountRecord.AccountRecordEditDto;
import ru.vapima.butjet4.service.AccountRecordService;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RequestMapping("/users/{id_user}/accounts/{id_acc}/accountrecords")
@RestController
public class AccountRecordController {

    private final AccountRecordService accountRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountRecordDto saveAccountRecord(@RequestBody @Valid AccountRecordAddDto accountRecordAddDto,
                                 @PathVariable("id_acc") Long idAccount,
                                 @PathVariable("id_user") Long idUser,
                                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountRecordService.addAccountRecord(accountRecordAddDto, idUser, idAccount);
    }


    @GetMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountRecordDto findAccountRecordById(@PathVariable("id") Long id,
                                     @PathVariable("id_acc") Long idAccount,
                                     @PathVariable("id_user") Long idUser,
                                     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountRecordService.getById(id, idUser, idAccount);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public void deleteAccountRecord(@PathVariable("id") Long id,
                       @PathVariable("id_acc") Long idAccount,
                       @PathVariable("id_user") Long idUser,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        accountRecordService.deleteById(id, idUser, idAccount);
    }


    @GetMapping
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public List<AccountRecordDto> listAccountRecords(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                       @PathVariable("id_acc") Long idAccount,
                                       @PathVariable("id_user") Long idUser,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountRecordService.getAll(idUser, idAccount, pageable);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public AccountRecordDto updateAccountRecord(@RequestBody @Valid AccountRecordEditDto accountRecordEditDto,
                                   @PathVariable("id") Long id,
                                   @PathVariable("id_acc") Long idAccount,
                                   @PathVariable("id_user") Long idUser,
                                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return accountRecordService.updateAccountRecord(accountRecordEditDto, id, idAccount, idUser);
    }
}
