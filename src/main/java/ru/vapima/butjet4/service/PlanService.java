package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.PlanDto;

import java.util.List;

public interface PlanService {
    List<PlanDto> getAll(Long idUser, Pageable pageable);

    PlanDto getById(Long id, Long idUser);

    void deleteById(Long id, Long idUser);

    PlanDto addPlan(PlanDto planDto, Long idUser);

    PlanDto updatePlan(PlanDto planDto, Long id, Long idUser);
}
