package ru.vapima.butjet4.dto.user;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "email")
public class UserEditDto {
    private String name;
    private String email;
    private String hashPassword;
}

