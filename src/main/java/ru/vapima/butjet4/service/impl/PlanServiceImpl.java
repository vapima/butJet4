package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.plan.PlanAddDto;
import ru.vapima.butjet4.dto.plan.PlanDto;
import ru.vapima.butjet4.dto.plan.PlanEditDto;
import ru.vapima.butjet4.mapper.PlanMapper;
import ru.vapima.butjet4.model.db.Plan;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.repository.PlanRepository;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.PlanService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final PlanMapper mapper;


    @Override
    public List<PlanDto> getAll(Long idUser, Pageable pageable) {
        List<Plan> plans = planRepository.findAllByUserId(idUser, pageable);
        return plans
                .stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PlanDto getById(Long id, Long idUser) {
        Plan plan = planRepository.findByIdAndUserId(id, idUser);
        return mapper.toDto(plan);
    }

    @Override
    public void deleteById(Long id, Long idUser) {
        Plan plan = planRepository.findByIdAndUserId(id, idUser);
        planRepository.delete(plan);
    }

    @Override
    public PlanDto addPlan(PlanAddDto planAddDto, Long idUser) {
        Plan planInput = mapper.fromDto(planAddDto);
        User user = userRepository.getOne(idUser);
        planInput.setUser(user);
        Plan save = planRepository.save(planInput);
        return mapper.toDto(save);
    }

    @Override
    public PlanDto updatePlan(PlanEditDto planEditDto, Long id, Long idUser) {
        User user = userRepository.getOne(idUser);
        Plan plan = planRepository.findByIdAndUserId(id, idUser);
        mapper.patchFromEditDto(planEditDto, plan);
        plan.setUser(user);
        plan.setId(id);
        return mapper.toDto(planRepository.save(plan));
    }
}
