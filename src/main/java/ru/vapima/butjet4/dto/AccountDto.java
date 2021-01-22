package ru.vapima.butjet4.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AccountDto {
    private Long id;
    private String name;
    private Boolean isActive;
}
