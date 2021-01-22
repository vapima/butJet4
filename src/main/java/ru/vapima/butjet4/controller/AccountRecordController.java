package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.AccountRecordDto;
import ru.vapima.butjet4.service.AccountRecordService;

import java.util.List;


@AllArgsConstructor
@RequestMapping("/users/{id_user}/accounts/{id_acc}/accountrecords")
@RestController
public class AccountRecordController {

    private final AccountRecordService accountRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountRecordDto save(@RequestBody AccountRecordDto accountRecordDto, @PathVariable("id_acc") Long idAccount, @PathVariable("id_user") Long idUser) {
        return accountRecordService.addAccountRecord(accountRecordDto, idUser, idAccount);
    }


    @GetMapping("/{id}")
    public AccountRecordDto findById(@PathVariable("id") Long id, @PathVariable("id_acc") Long idAccount, @PathVariable("id_user") Long idUser) {
        return accountRecordService.getById(id, idUser, idAccount);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id, @PathVariable("id_acc") Long idAccount, @PathVariable("id_user") Long idUser) {
        accountRecordService.deleteById(id, idUser, idAccount);
    }


    @GetMapping
    public List<AccountRecordDto> list(@PageableDefault(value = 10, page = 0) Pageable pageable, @PathVariable("id_acc") Long idAccount, @PathVariable("id_user") Long idUser) {
        return accountRecordService.getAll(idUser, idAccount, pageable);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public AccountRecordDto update(@RequestBody AccountRecordDto accountRecordDto, @PathVariable("id") Long id, @PathVariable("id_acc") Long idAccount, @PathVariable("id_user") Long idUser) {
        return accountRecordService.updateAccountRecord(accountRecordDto, id, idAccount, idUser);
    }
}
