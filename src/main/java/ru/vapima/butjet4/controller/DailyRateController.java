package ru.vapima.butjet4.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vapima.butjet4.dto.dailyRate.DailyRateDto;
import ru.vapima.butjet4.service.DailyRateService;


@RequestMapping("/users/{id_user}/rate")
@RestController
@AllArgsConstructor
public class DailyRateController {

    private final DailyRateService dailyRateService;

    @GetMapping
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public DailyRateDto takeRate(@PathVariable("id_user") Long idUser,
                                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return dailyRateService.getDailyRateByUserId(idUser);
    }


}
