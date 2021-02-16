package ru.vapima.butjet4.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;
import ru.vapima.butjet4.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users")
@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody @Valid UserRegistartionDto userRegistartionDto) {
        log.info("New User: " + userRegistartionDto.getEmail());
        return userService.addUser(userRegistartionDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto findUserById(@PathVariable("id") Long id,
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public void deleteUser(@PathVariable("id") Long id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        userService.deleteById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> listUsers(@PageableDefault Pageable pageable,
                              @RequestParam(value = "state", required = false, defaultValue = "ACTIVE") String state) {
        return userService.getAll(state, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto updateUser(@RequestBody @Valid UserEditDto userEditDto,
                          @PathVariable("id") Long id,
                          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return userService.updateUser(userEditDto, id);
    }
}
