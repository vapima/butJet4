package com.github.template.service;

import com.github.template.dto.PlanDto;
import com.github.template.mapper.PlanMapper;
import com.github.template.model.db.Plan;
import com.github.template.model.db.User;
import com.github.template.repository.PlanRepository;
import com.github.template.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        List<Plan> plans = planRepository.findAllByUserId(idUser,pageable);
        return plans
                .stream()
                .filter(f -> f.getUser().getId().equals(idUser))
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PlanDto getById(Long id, Long idUser) {
        Plan plan = planRepository.getOne(id);
        if (!plan.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your plan.");
        }
        return mapper.toDto(plan);
    }

    @Override
    public void deleteById(Long id, Long idUser) {
        Plan plan = planRepository.getOne(id);
        if (!plan.getUser().getId().equals(idUser)) {
            throw new IllegalArgumentException("That's not your plan.");
        }
        planRepository.deleteById(id);
    }

    @Override
    public PlanDto addPlan(PlanDto planDto, Long idUser) {
        Plan plan = mapper.fromDto(planDto);
        User user = userRepository.getOne(idUser);
        plan.setUser(user);
        Plan save = planRepository.save(plan);
        return mapper.toDto(save);
    }

    @Override
    public PlanDto updatePlan(PlanDto planDto, Long id, Long idUser) {
        User user = userRepository.getOne(idUser);
        Plan plan = planRepository.getOne(id);
        if (!plan.getUser().equals(user)) {
            throw new IllegalArgumentException("That's not your plan.");
        }
        mapper.formEditToPlan(planDto,plan);
        plan.setUser(user);
        plan.setId(id);
        return mapper.toDto(planRepository.save(plan));
    }
}
