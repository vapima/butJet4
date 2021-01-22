package ru.vapima.butjet4.dto.account;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AccountAddDto {
    private String name;
    private Boolean isActive;
}
