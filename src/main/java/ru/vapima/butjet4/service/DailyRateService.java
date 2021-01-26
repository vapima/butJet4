package ru.vapima.butjet4.service;

import ru.vapima.butjet4.dto.dailyRate.DailyRateDto;

public interface DailyRateService {
    DailyRateDto getDailyRateByUserId(Long id);
}
