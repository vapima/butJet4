package ru.vapima.butjet4.dto.authJwt;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AuthResponse {
    private String jwtToken;
}
