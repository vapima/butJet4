package com.github.template.service;

import com.github.template.dto.UserDto;
import com.github.template.mapper.UserMapper;
import com.github.template.model.db.Role;
import com.github.template.model.db.State;
import com.github.template.model.db.User;
import com.github.template.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public List<UserDto> getAll(String state, Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .filter(o -> o.getState()!=null && o.getState().equals(State.valueOf(state.toUpperCase())))
                .map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return mapper.toDto(userRepository.getOne(id));
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.getOne(id);
        user.setState(State.DELETED);
        userRepository.save(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.fromDtoFormRegistaration(userDto);
        if (userRepository.existsUserByEmail(userDto.getEmail())) {throw new IllegalArgumentException("Email already exist.");}
        user.setState(State.ACTIVE);
        user.setRole(Role.ROLE_USER);
        return mapper.toDto(userRepository.save(user));

    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.getOne(id);
        mapper.fromDtoFormEdit(userDto,user);
        user.setId(id);
        return mapper.toDto(userRepository.save(user));
    }
}
