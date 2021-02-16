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
@Sql(scripts = {"/test_user_with_accs_and_records.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AccountRecordControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    public static final String NEW_ACCOUNT_RECORDS_JSON="{\"amount\":-200,\"description\":\"test_accountrecord\",\"dateTime\":\"2016-06-22T19:10:25\"}";
    public static final String REQUEST_FIND_ALL_ACCOUNTS_RECORDS_ARRAY = "[{\"id\":1,\"amount\":-200,\"description\":\"test_accountrecord\",\"dateTime\":\"2016-06-22T19:10:25\"}]";
    public static final String ENDPOINT="/users/1/accounts/1/accountrecords";

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void saveAccountRecord() {
        mockMvc.perform(post(ENDPOINT)
                .content(NEW_ACCOUNT_RECORDS_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("test_accountrecord"))
                .andExpect(jsonPath("$.amount").value(-200))
                .andExpect(jsonPath("$.dateTime").value("2016-06-22T19:10:25"));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void findAccountRecordById() {
        mockMvc.perform(get(ENDPOINT+"/{accRecId}", 1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("test_accountrecord"))
                .andExpect(jsonPath("$.amount").value(-200))
                .andExpect(jsonPath("$.dateTime").value("2016-06-22T19:10:25"));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void deleteAccountRecord() {
        mockMvc.perform(delete(ENDPOINT+"/{accrecId}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void listAccountRecords() {
        mockMvc.perform(get(ENDPOINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(REQUEST_FIND_ALL_ACCOUNTS_RECORDS_ARRAY));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void updateAccountRecord() {
        mockMvc.perform(patch(ENDPOINT+"/{accId}", 1)
                .content("{\"description\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("test_update"))
                .andExpect(jsonPath("$.amount").value(-200))
                .andExpect(jsonPath("$.dateTime").value("2016-06-22T19:10:25"));
    }
}