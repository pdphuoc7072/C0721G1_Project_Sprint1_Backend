package com.codegym.controller;

import antlr.build.Tool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_findDetailEmployeeById {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findDetailEmployeeById_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/employee/detail/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void findDetailEmployeeById_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/employee/detail/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void findDetailEmployeeById_3() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/employee/detail/{id}", "3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void findDetailEmployeeById_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/employee/detail/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Bùi Thường Tính"))
                .andExpect(jsonPath("$.birthday").value("28/06/2000"))
                .andExpect(jsonPath("$.address").value("Quảng Bình"))
                .andExpect(jsonPath("$.gender").value(1))
                .andExpect(jsonPath("$.position.name").value("Quản lý")) ;
    }
}
