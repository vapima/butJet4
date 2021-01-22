package ru.vapima.butjet4.dto.plan;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PlanDto {
    private Long id;
    private String name;
    private Long amount;
    private LocalDate expirationDate;
}
