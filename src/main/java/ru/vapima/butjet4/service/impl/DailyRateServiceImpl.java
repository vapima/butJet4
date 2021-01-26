package ru.vapima.butjet4.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.dto.dailyRate.DailyRateDto;
import ru.vapima.butjet4.model.db.Account;
import ru.vapima.butjet4.model.db.Plan;
import ru.vapima.butjet4.repository.AccountRecordRepository;
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
    private final AccountRecordRepository accountRecordRepository;

    private Long totalAcc(List<Account> accs) {
        Long total = 0L;
        for (Account a : accs) {
                total += accountRecordRepository.getAmountFromLastRecordByAccountId(a.getId());
        }
        return total;
    }
    private Long totalAccActive(List<Account> accs) {
        Long total = 0L;
        for (Account a : accs) {
            if (a.getIsActive()) {
                total += accountRecordRepository.getAmountFromLastRecordByAccountId(a.getId());
            }
        }
        return total;
    }

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
        Long allAmountAccount=accountRepository.getSumAmountAllAccountsByUserId(id);
        Long activeAmountAccount=accountRepository.getSumAmountActiveAccountsByUserId(id);
        List<Plan> planList = planRepository.findAllByUserId(id);
        return DailyRateDto.builder()
                .rate(getRate(planList,activeAmountAccount))
                  .allAccounts(allAmountAccount)
                  .allPlans(totalPlan(planList))
                  .allActiveAccounts(activeAmountAccount)
                  .allPlansInThisMounth(getAllPlansInThisMounth(planList))
                .build();
    }
}

