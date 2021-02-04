package ru.vapima.butjet4.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.config.CustomTokenAuthentication;
import ru.vapima.butjet4.dto.dailyRate.DailyRateDto;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;
import ru.vapima.butjet4.repository.AccountRecordRepository;
import ru.vapima.butjet4.service.DailyRateService;
import ru.vapima.butjet4.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users/{id_user}/rate")
@RestController
@AllArgsConstructor
public class DailyRateController {

    private final DailyRateService dailyRateService;

    @GetMapping
    @PreAuthorize("#idUser.equals(#customTokenAuthentication.id)")
    public DailyRateDto findById(@PathVariable("id_user") Long idUser,
                                 CustomTokenAuthentication customTokenAuthentication) {
        return dailyRateService.getDailyRateByUserId(idUser);
    }


}
