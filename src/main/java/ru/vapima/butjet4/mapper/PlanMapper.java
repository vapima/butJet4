package com.github.template.mapper;

import com.github.template.dto.PlanDto;
import com.github.template.dto.UserDto;
import com.github.template.model.db.Plan;
import com.github.template.model.db.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    PlanDto toDto(Plan plan);

    Plan fromDto(PlanDto planDto);

    @Mapping(target = "id",ignore = true)
    Plan fromDtoFormCreat(PlanDto planDto);

    @Mapping(target = "id",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void formEditToPlan(PlanDto planDto, @MappingTarget Plan plan);
}
