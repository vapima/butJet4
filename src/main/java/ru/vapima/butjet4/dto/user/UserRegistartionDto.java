package ru.vapima.butjet4.dto.user;

import lombok.*;
import ru.vapima.butjet4.model.db.Role;
import ru.vapima.butjet4.model.db.State;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "email")
public class UserRegistartionDto {
    @NotBlank
    @Size(min=3,max=30)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=7,max=15)
    private String hashPassword;
}

