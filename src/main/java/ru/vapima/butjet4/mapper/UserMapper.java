package ru.vapima.butjet4.mapper;

import org.mapstruct.*;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;
import ru.vapima.butjet4.model.db.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User fromDto(UserDto userDto);
    User fromDto(UserRegistartionDto userRegistartionDto);
    User fromDto(UserEditDto userEditDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(UserEditDto userEditDto, @MappingTarget User user);

}
