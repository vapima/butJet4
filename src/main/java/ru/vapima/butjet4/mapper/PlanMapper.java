package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.PlanDto;
import ru.vapima.butjet4.model.db.Plan;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    PlanDto toDto(Plan plan);

    Plan fromDto(PlanDto planDto);

    @Mapping(target = "id", ignore = true)
    Plan fromDtoFormCreat(PlanDto planDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToPlan(PlanDto planDto, @MappingTarget Plan plan);
}
