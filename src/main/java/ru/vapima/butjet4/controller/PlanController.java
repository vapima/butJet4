package com.github.template.controller;

import com.github.template.dto.PlanDto;
import com.github.template.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users/{id_user}/plans")
@AllArgsConstructor
@RestController
public class PlanController {

    private final PlanService planService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanDto save(@RequestBody PlanDto planDTO, @PathVariable("id_user") Long idUser) {
        return planService.addPlan(planDTO, idUser);
    }


    @GetMapping("/{id}")
    public PlanDto findById(@PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        return planService.getById(id, idUser);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        planService.deleteById(id, idUser);
    }


    @GetMapping
    public List<PlanDto> list(@PageableDefault(value = 10,page = 0) Pageable pageable, @PathVariable("id_user") Long idUser) {
        return planService.getAll(idUser, pageable);
    }


    @PatchMapping("/{id}")
    public PlanDto update(@RequestBody PlanDto planDTO, @PathVariable("id") Long id, @PathVariable("id_user") Long idUser) {
        return planService.updatePlan(planDTO, id, idUser);
    }
}
