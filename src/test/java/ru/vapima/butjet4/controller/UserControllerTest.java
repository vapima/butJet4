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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;


    public static final String NEW_USER_JSON = "{\"id\":1,\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}";
    public static final String NEW_USER_WITH_EXIST_EMAIL_JSON = "{\"id\":1,\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}";
    public static final String REQUEST_FIND_ALL_USERS_ARRAY = "[{\"id\":1,\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"$2a$10$ISmet2jVjPpUMp7xVRasTe1q4x0F5H3y4dffJ0/yX4iOQ9JFQxdzq\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}]";


    @SneakyThrows
    @Test
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveNewUser() {
        mockMvc.perform(post("/users")
                .content(NEW_USER_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.tt"));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    @Sql(scripts = {"/test_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findUserById() {
        mockMvc.perform(get("/users/{accountId}", 1)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.tt"));
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    @Sql(scripts = {"/test_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteUserById() {
        mockMvc.perform(delete("/users/{accountId}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    @Sql(scripts = {"/test_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getListOfAllUserByStateActive() {
        mockMvc.perform(get("/users")
                .param("state", "active")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(REQUEST_FIND_ALL_USERS_ARRAY));
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    @Sql(scripts = {"/test_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void updateUserName() {
        mockMvc.perform(patch("/users/{accountId}", 1)
                .content("{\"name\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test_update"))
                .andExpect(jsonPath("$.email").value("test@test.tt"));
    }

    @SneakyThrows
    @Test
    @Sql(scripts = {"/test_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/delete_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveNewUserWithExistEmailShouldBeError() {
        mockMvc.perform(post("/users")
                .content(NEW_USER_WITH_EXIST_EMAIL_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("This email already exist."))
                .andReturn();
    }

}