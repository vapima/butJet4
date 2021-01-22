package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(String state, Pageable pageable);

    UserDto getById(Long id);

    void deleteById(Long id);

    UserDto addUser(UserRegistartionDto userRegistartionDto);

    UserDto updateUser(UserEditDto userEditDto, Long id);
}
