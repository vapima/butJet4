package com.github.template.service;

import com.github.template.dto.UserDto;
import com.github.template.model.db.State;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(String state, Pageable pageable);

    UserDto getById(Long id);

    void deleteById(Long id);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);
}
