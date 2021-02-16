package ru.vapima.butjet4.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.plan.PlanAddDto;
import ru.vapima.butjet4.dto.plan.PlanDto;
import ru.vapima.butjet4.dto.plan.PlanEditDto;
import ru.vapima.butjet4.service.PlanService;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users/{id_user}/plans")
@AllArgsConstructor
@RestController
public class PlanController {

    private final PlanService planService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public PlanDto savePlan(@RequestBody @Valid PlanAddDto planAddDto,
                        @PathVariable("id_user") Long idUser,
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return planService.addPlan(planAddDto, idUser);
    }


    @GetMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public PlanDto findPlanById(@PathVariable("id") Long id,
                            @PathVariable("id_user") Long idUser,
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return planService.getById(id, idUser);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public void deletePlan(@PathVariable("id") Long id,
                       @PathVariable("id_user") Long idUser,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        planService.deleteById(id, idUser);
    }


    @GetMapping
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public List<PlanDto> listPlans(@PageableDefault(value = 10, page = 0) Pageable pageable,
                              @PathVariable("id_user") Long idUser,
                              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return planService.getAll(idUser, pageable);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("#idUser.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public PlanDto updatePlan(@RequestBody @Valid PlanEditDto planEditDto,
                          @PathVariable("id") Long id,
                          @PathVariable("id_user") Long idUser,
                          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return planService.updatePlan(planEditDto, id, idUser);
    }
}
