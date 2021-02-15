package ru.vapima.butjet4.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vapima.butjet4.BaseTest;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DailyRateControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;


    @WithUserDetails(value = "test@user.io")
    @SneakyThrows
    @Test
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/user_for_calculate_daily_rate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void takeDailyRateByUserId() {
        MvcResult mvcResult = mockMvc.perform(get("/users/1/rate"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.allPlans").value(-2000))
                .andExpect(jsonPath("$.allAccounts").value(6500))
                .andReturn();
    }

}