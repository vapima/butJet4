package ru.vapima.butjet4.dto.plan;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PlanAddDto {
    @NotBlank
    @Size(min=3,max=30)
    private String name;
    @NotNull
    private Long amount;
    @NotNull
    private LocalDate expirationDate;
}
