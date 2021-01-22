package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;
import ru.vapima.butjet4.mapper.UserMapper;
import ru.vapima.butjet4.model.db.Role;
import ru.vapima.butjet4.model.db.State;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.UserService;

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
                .filter(o -> o.getState() != null && o.getState().equals(State.valueOf(state.toUpperCase())))
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
    public UserDto addUser(UserRegistartionDto userRegistartionDto) {
        User user = mapper.fromDto(userRegistartionDto);
        user.setState(State.ACTIVE);
        user.setRole(Role.ROLE_USER);
     /*   User save=null;
        try { save = userRepository.save(user);}
        catch (DataIntegrityViolationException ex){throw new IllegalArgumentException(ex.getMessage());}*/
        User save = userRepository.save(user);
        return mapper.toDto(save);

    }

    @Override
    public UserDto updateUser(UserEditDto userEditDto, Long id) {
        User user = userRepository.getOne(id);
        mapper.patchFromEditDto(userEditDto, user);
        user.setId(id);
        return mapper.toDto(userRepository.save(user));
    }
}
