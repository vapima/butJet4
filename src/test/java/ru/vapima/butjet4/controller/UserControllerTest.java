package ru.vapima.butjet4.controller;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vapima.butjet4.BaseTest;
import ru.vapima.butjet4.dto.user.UserEditDto;
import ru.vapima.butjet4.dto.user.UserRegistartionDto;

import ru.vapima.butjet4.model.db.Role;
import ru.vapima.butjet4.model.db.State;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.repository.UserRepository;
import ru.vapima.butjet4.service.UserService;


import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Sql(scripts = {"/big_plans_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private Long id;



    @BeforeAll
    void prepareData() {
         id=userRepository.save(User.builder()
                    .email("test@test.tt")
                    .name("test")
                    .hashPassword("test_password")
                    .state(State.ACTIVE)
                    .role(Role.ROLE_USER)
                    .build())
                .getId();
    }
    @AfterAll
    void finish(){
        userRepository.deleteById(id);
    }
    @SneakyThrows
    @Test
    void saveNewUser() {
        MvcResult mvcResult = mockMvc.perform(post("/users")
                .content("{\"id\":1,\"name\":\"test\",\"email\":\"test@test2.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@test2.tt"))
                .andExpect(jsonPath("$.hashPassword").value("test_password"))
                .andReturn();
        userRepository.deleteById(Long.parseLong(new JSONObject(mvcResult.getResponse().getContentAsString()).get("id").toString()));
    }

    @SneakyThrows
    @Test
    void findUserById() {
        mockMvc.perform(get("/users/{accountId}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":"+id+",\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}"));
    }

    @SneakyThrows
    @Test
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
                .role(Role.ROLE_USER)
                .build());
    }

    @SneakyThrows
    @Test
    void getListOfAllUserByStateActive() {
        mockMvc.perform(get("/users")
                .param("state", "active")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json("[{\"id\":"+id+",\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}]"));
    }

    @SneakyThrows
    @Test
    void updateUserName() {
        mockMvc.perform(patch("/users/{accountId}", id)
                .content("{\"name\":\"test_update\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":"+id+",\"name\":\"test_update\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}"));
        mockMvc.perform(patch("/users/{accountId}", "1")
                .content("{\"name\":\"test\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":"+id+",\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}"));
    }

    @SneakyThrows
    @Test
    void saveNewUserWithExistEmailShouldBeError() {
         mockMvc.perform(post("/users")
                .content("{\"id\":1,\"name\":\"test\",\"email\":\"test@test.tt\",\"hashPassword\":\"test_password\",\"state\":\"ACTIVE\",\"role\":\"ROLE_USER\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("This email already exist."))
                .andReturn();
       }

}