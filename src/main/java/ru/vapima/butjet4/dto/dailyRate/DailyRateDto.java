package ru.vapima.butjet4.dto.dailyRate;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class DailyRateDto {
    private Long rate;
    private Long allPlans;
    private Long allAccounts;
    private Long allActiveAccounts;
    private Long allPlansInThisMounth;
}

