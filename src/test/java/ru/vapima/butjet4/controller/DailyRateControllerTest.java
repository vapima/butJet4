package ru.vapima.butjet4.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.vapima.butjet4.BaseTest;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.impl.DailyRateServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DailyRateControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;



    @SneakyThrows
    @Test
    @Sql(scripts = {"/user_for_calculate_daily_rate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_for_calculate_daily_rate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void takeDailyRateByUserId() {
        mockMvc.perform(get("/users/1/rate"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.allPlans").value(-2000))
                .andExpect(jsonPath("$.allAccounts").value(6500))
                .andExpect(jsonPath("$.allActiveAccounts").value(4500));
    }

}