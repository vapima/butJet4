package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.plan.PlanAddDto;
import ru.vapima.butjet4.dto.plan.PlanDto;
import ru.vapima.butjet4.dto.plan.PlanEditDto;
import ru.vapima.butjet4.model.db.Plan;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    PlanDto toDto(Plan plan);

    Plan fromDto(PlanDto planDto);
    Plan fromDto(PlanAddDto planAddDto);
    Plan fromDto(PlanEditDto planEditDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(PlanEditDto planEditDto, @MappingTarget Plan plan);



}
