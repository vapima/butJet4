package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("customUserDetailsService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<UserDto> getAll(String state, Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .filter(o -> o.getState() != null && o.getState().equals(State.valueOf(state.toUpperCase())))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.getOne(id);
        return mapper.toDto(user);
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
        user.setHashPassword(bCryptPasswordEncoder.encode(userRegistartionDto.getHashPassword()));
        user.setState(State.ACTIVE);
        user.setRole(Role.ROLE_USER);
        User save = null;
        try {
            save = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("This email already exist.");
        }
        return mapper.toDto(save);
    }

    @Override
    public UserDto updateUser(UserEditDto userEditDto, Long id) {
        User user = userRepository.getOne(id);
        mapper.patchFromEditDto(userEditDto, user);
        user.setId(id);
        User save = null;
        try {
            save = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("This email already exist.");
        }
        return mapper.toDto(save);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by this email " + s);
        }
        return user;
    }
}
