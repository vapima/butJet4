package ru.vapima.butjet4.dto.plan;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PlanEditDto {
    private String name;
    private Long amount;
    private LocalDate expirationDate;
}
