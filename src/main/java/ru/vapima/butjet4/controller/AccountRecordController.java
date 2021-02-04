package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.config.CustomTokenAuthentication;
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
    @PreAuthorize("#idUser.equals(#customTokenAuthentication.id)")
    public AccountRecordDto save(@RequestBody @Valid AccountRecordAddDto accountRecordAddDto,
                                 @PathVariable("id_acc") Long idAccount,
                                 @PathVariable("id_user") Long idUser,
                                 CustomTokenAuthentication customTokenAuthentication) {
        return accountRecordService.addAccountRecord(accountRecordAddDto, idUser, idAccount);
    }


    @GetMapping("/{id}")
    @PreAuthorize("#idUser.equals(#customTokenAuthentication.id)")
    public AccountRecordDto findById(@PathVariable("id") Long id,
                                     @PathVariable("id_acc") Long idAccount,
                                     @PathVariable("id_user") Long idUser,
                                     CustomTokenAuthentication customTokenAuthentication) {
        return accountRecordService.getById(id, idUser, idAccount);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("#idUser.equals(#customTokenAuthentication.id)")
    public void delete(@PathVariable("id") Long id,
                       @PathVariable("id_acc") Long idAccount,
                       @PathVariable("id_user") Long idUser,
                       CustomTokenAuthentication customTokenAuthentication) {
        accountRecordService.deleteById(id, idUser, idAccount);
    }


    @GetMapping
    @PreAuthorize("#idUser.equals(#customTokenAuthentication.id)")
    public List<AccountRecordDto> list(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                       @PathVariable("id_acc") Long idAccount,
                                       @PathVariable("id_user") Long idUser,
                                       CustomTokenAuthentication customTokenAuthentication) {
        return accountRecordService.getAll(idUser, idAccount, pageable);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @PreAuthorize("#id.equals(#customTokenAuthentication.id)")
    public AccountRecordDto update(@RequestBody @Valid AccountRecordEditDto accountRecordEditDto,
                                   @PathVariable("id") Long id,
                                   @PathVariable("id_acc") Long idAccount,
                                   @PathVariable("id_user") Long idUser,
                                   CustomTokenAuthentication customTokenAuthentication) {
        return accountRecordService.updateAccountRecord(accountRecordEditDto, id, idAccount, idUser);
    }
}
