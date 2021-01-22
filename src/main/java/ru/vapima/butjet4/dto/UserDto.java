package com.github.template.dto;

import com.github.template.model.db.Role;
import com.github.template.model.db.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

