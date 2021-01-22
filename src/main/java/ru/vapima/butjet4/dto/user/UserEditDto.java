package ru.vapima.butjet4.dto.user;

import lombok.*;

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
public class UserEditDto {
    @Size(min=3,max=30)
    private String name;
    @Email
    private String email;
    @Size(min=7,max=15)
    private String hashPassword;
}

