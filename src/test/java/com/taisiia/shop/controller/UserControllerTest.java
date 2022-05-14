package com.taisiia.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taisiia.shop.domain.dto.UserDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldSaveUser() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("taechka97@gmail.com")
                                .firstName("Anna")
                                .lastName("Tkaczyk")
                                .password("aaaaaaaaaaaaaa")
                                .confirmPassword("aaaaaaaaaaaaaa")
                        .build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.email").value("taechka97@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Anna"))
                .andExpect(jsonPath("$.lastName").value("Tkaczyk"))
                .andExpect(jsonPath("$.revisionNumber").doesNotExist());

    }

    @Test
    void shouldNotSaveUserWhenPasswordAndConfirmPasswordAreDifferent() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("taechka97@gmail.com")
                                .firstName("Anna")
                                .lastName("Tkaczyk")
                                .password("aaaaaaaaaaaaaa")
                                .confirmPassword("aaaaaaaaaaaaaa$")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Password and password confirm not the same"));
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.password").doesNotExist())
//                .andExpect(jsonPath("$.email").value("taechka97@gmail.com"))
//                .andExpect(jsonPath("$.firstName").value("Anna"))
//                .andExpect(jsonPath("$.lastName").value("Tkaczyk"))
//                .andExpect(jsonPath("$.revisionNumber").doesNotExist());

    }
}
