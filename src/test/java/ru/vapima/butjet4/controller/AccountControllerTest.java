package ru.vapima.butjet4.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/test_user_with_accs.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AccountControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    public static final String NEW_ACCOUNT_JSON="{\"name\":\"test_account\", \"isActive\":true}";
    public static final String REQUEST_FIND_ALL_ACCOUNTS_ARRAY = "[{\"id\":1,\"name\":\"test_account\",\"isActive\":true}]";
    public static final String ENDPOINT="/users/1/accounts";

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void saveAccount() {
        mockMvc.perform(post(ENDPOINT)
                .content(NEW_ACCOUNT_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test_account"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void findAccountById() {
        mockMvc.perform(get(ENDPOINT+"/{accId}", 1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test_account"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void deleteAccount() {
        mockMvc.perform(delete(ENDPOINT+"/{accId}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void listAccounts() {
        mockMvc.perform(get(ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(REQUEST_FIND_ALL_ACCOUNTS_ARRAY));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void updateAccount() {
        mockMvc.perform(patch(ENDPOINT+"/{accId}", 1)
                .content("{\"name\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test_update"))
                .andExpect(jsonPath("$.isActive").value(true));
    }
}