package ru.vapima.butjet4.controller;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vapima.butjet4.BaseTest;
import ru.vapima.butjet4.model.db.Role;
import ru.vapima.butjet4.model.db.State;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private Long id;

    public static final String NEW_USER_JSON = "{\"id\":1,\"name\":\"test\",\"email\":\"test@test2.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}";
    public static final String NEW_USER_WITH_EXIST_EMAIL_JSON = "{\"id\":1,\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}";

    @BeforeAll
    void prepareData() {
        id = userRepository.save(User.builder()
                .email("test@test.tt")
                .name("test")
                .hashPassword("test_password")
                .state(State.ACTIVE)
                .role(Role.ROLE_ADMIN)
                .build())
                .getId();
    }

    @AfterAll
    void finish() {
        userRepository.deleteById(id);
    }

    @SneakyThrows
    @Test
    void saveNewUser() {
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .content(NEW_USER_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@test2.tt"))
                .andReturn();
        userRepository.deleteById(Long.parseLong(new JSONObject(mvcResult.getResponse().getContentAsString()).get("id").toString()));
    }

    @WithUserDetails(value = "test@test.tt")
    @SneakyThrows
    @Test
    void findUserById() {
        mockMvc.perform(get("/users/{accountId}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":" + id + ",\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}"));
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    void deleteUserById() {
        mockMvc.perform(delete("/users/{accountId}", id))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        userRepository.save(User.builder()
                .id(id)
                .email("test@test.tt")
                .name("test")
                .hashPassword("test_password")
                .state(State.ACTIVE)
                .role(Role.ROLE_ADMIN)
                .build())
                .getId();
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    void getListOfAllUserByStateActive() {
        mockMvc.perform(get("/users")
                .param("state", "active")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json("[{\"id\":" + id + ",\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}]"));
    }

    @SneakyThrows
    @Test
    @WithUserDetails(value = "test@test.tt")
    void updateUserName() {
        mockMvc.perform(patch("/users/{accountId}", id)
                .content("{\"name\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":" + id + ",\"name\":\"test_update\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_ADMIN\"}"));
        userRepository.save(User.builder()
                .id(id)
                .email("test@test.tt")
                .name("test")
                .hashPassword("test_password")
                .state(State.ACTIVE)
                .role(Role.ROLE_ADMIN)
                .build())
                .getId();
    }

    @SneakyThrows
    @Test
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