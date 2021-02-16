package ru.vapima.butjet4.controller;

import liquibase.pro.packaged.S;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.vapima.butjet4.BaseTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/test_user_with_plans.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PlanControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    public static final String NEW_ACCOUNT_JSON="{\"name\":\"test_plan\",\"amount\":100,\"expirationDate\":\"2030-06-01\"}";
    public static final String REQUEST_FIND_ALL_PLANS_ARRAY = "[{\"id\":1,\"name\":\"test_plan\",\"amount\":100,\"expirationDate\":\"2030-06-01\"}]";

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void savePlan() {
        mockMvc.perform(post("/users/1/plans")
                .content(NEW_ACCOUNT_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test_plan"))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.expirationDate").value("2030-06-01"));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void findPlanById() {
        mockMvc.perform(get("/users/1/plans/{planId}", 1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test_plan"))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.expirationDate").value("2030-06-01"));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void deletePlan() {
        mockMvc.perform(delete("/users/1/plans/{planId}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void listPlan() {
        mockMvc.perform(get("/users/1/plans")
                .param("state", "active")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(REQUEST_FIND_ALL_PLANS_ARRAY));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void updatePlan() {
        mockMvc.perform(patch("/users/1/plans/{planId}", 1)
                .content("{\"name\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test_update"))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.expirationDate").value("2030-06-01"));
    }
}