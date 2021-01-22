package ru.vapima.butjet4.service;

import org.springframework.data.domain.Pageable;
import ru.vapima.butjet4.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(String state, Pageable pageable);

    UserDto getById(Long id);

    void deleteById(Long id);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);
}
