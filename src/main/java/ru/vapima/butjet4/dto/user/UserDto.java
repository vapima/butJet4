package ru.vapima.butjet4.dto.user;

import lombok.*;
import ru.vapima.butjet4.model.db.Role;
import ru.vapima.butjet4.model.db.State;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "email")
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String hashPassword;
    private State state;
    private Role role;
}

