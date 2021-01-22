package ru.vapima.butjet4.dto.account;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AccountEditDto {
    @Size(min=3,max=30)
    private String name;
    private Boolean isActive;
}
