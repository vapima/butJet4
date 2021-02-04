package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.dailyRate.DailyRateDto;
import ru.vapima.butjet4.model.db.Plan;
import ru.vapima.butjet4.repository.AccountRepository;
import ru.vapima.butjet4.repository.PlanRepository;
import ru.vapima.butjet4.service.DailyRateService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@Service
public class DailyRateServiceImpl implements DailyRateService {
    private final AccountRepository accountRepository;
    private final PlanRepository planRepository;

    private Long totalPlan(List<Plan> plans) {
        Long totalPlans = 0L;
        for (Plan p : plans) {
            totalPlans += p.getAmount();
        }
        return totalPlans;
    }


    public Long daysLeftMonth() {
        return (long) Period.between(LocalDate.now(), LocalDate.now().plusMonths(1).withDayOfMonth(1)).getDays();
    }

    public Long getRate(List<Plan> planList, Long activeAccounts) {
        Long rdn = (activeAccounts + getAllPlansInThisMounth(planList))
                / daysLeftMonth();
        return rdn;
    }

    public Long getAllPlansInThisMounth(List<Plan> planList) {
        return planList.stream()
                .filter(p -> p.getExpirationDate().isBefore(LocalDate.now().plusMonths(1).withDayOfMonth(1)))
                .mapToLong(Plan::getAmount)
                .sum();
    }


    @Override
    public DailyRateDto getDailyRateByUserId(Long id) {
        Long allAmountAccount = accountRepository.getSumAmountAccountsByUserIdAAndIsActive(id, false);
        Long activeAmountAccount = accountRepository.getSumAmountAccountsByUserIdAAndIsActive(id, true);
        if (allAmountAccount == null) {
            allAmountAccount = 0L;
        }
        if (activeAmountAccount == null) {
            activeAmountAccount = 0L;
        }
        List<Plan> planList = planRepository.findAllByUserId(id);
        return DailyRateDto.builder()
                .rate(getRate(planList, activeAmountAccount))
                .allAccounts(allAmountAccount)
                .allPlans(totalPlan(planList))
                .allActiveAccounts(activeAmountAccount)
                .allPlansInThisMounth(getAllPlansInThisMounth(planList))
                .build();
    }
}

