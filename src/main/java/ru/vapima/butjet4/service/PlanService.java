package com.github.template.service;

import com.github.template.dto.PlanDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {
    List<PlanDto> getAll(Long idUser, Pageable pageable);

    PlanDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    PlanDto addPlan(PlanDto planDto, Long idUser);

    PlanDto updatePlan(PlanDto planDto, Long id, Long idUser);
}
