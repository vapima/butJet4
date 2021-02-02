package ru.vapima.butjet4.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet4.dto.user.UserDto;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody @Valid UserRegistartionDto userRegistartionDto) {
        return userService.addUser(userRegistartionDto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {

        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    public List<UserDto> list(@PageableDefault(value = 10, page = 0) Pageable pageable, @RequestParam(value = "state", required = false, defaultValue = "ACTIVE") String state) {
        return userService.getAll(state, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public UserDto update(@RequestBody @Valid UserEditDto userEditDto, @PathVariable("id") Long id) {
        return userService.updateUser(userEditDto, id);
    }
}
