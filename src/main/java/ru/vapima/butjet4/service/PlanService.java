package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.plan.PlanAddDto;
import ru.vapima.butjet4.dto.plan.PlanDto;
import ru.vapima.butjet4.dto.plan.PlanEditDto;

import java.util.List;

public interface PlanService {
    List<PlanDto> getAll(Long idUser, Pageable pageable);

    PlanDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    PlanDto addPlan(PlanAddDto planAddDtom, Long idUser);

    PlanDto updatePlan(PlanEditDto planEditDto, Long id, Long idUser);
}
