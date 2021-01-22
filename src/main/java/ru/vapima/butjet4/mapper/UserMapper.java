package com.github.template.mapper;

import com.github.template.dto.UserDto;
import com.github.template.model.db.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User fromDto(UserDto userDto);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "state",ignore = true)
    @Mapping(target = "role",ignore = true)
    User fromDtoFormRegistaration(UserDto userDto);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "state",ignore = true)
    @Mapping(target = "role",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void fromDtoFormEdit(UserDto userDto, @MappingTarget User user);
}
