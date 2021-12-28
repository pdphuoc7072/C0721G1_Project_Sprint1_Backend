package com.codegym.controller;

import com.codegym.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserController_editPassword {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void editPassword_userName_19() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setUsername(null);
        userDto.setPassword("111111");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editPassword_userName_20() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setUsername("");
        userDto.setPassword("111111");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }    @Test
    public void editPassword_password_21() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setUsername("admin");
        userDto.setPassword("++11__");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editPassword_password_22() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setUsername("admin");
        userDto.setPassword("1b");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editPassword_password_23() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setUsername("admin");
        userDto.setPassword("1b11111111111");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void editPassword_24() throws Exception {

        UserDto userDto = new UserDto();

        userDto.setId(1l);
        userDto.setPassword("tinh123");
        userDto.setUsername("admin");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/editPass")
                        .content(this.objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
