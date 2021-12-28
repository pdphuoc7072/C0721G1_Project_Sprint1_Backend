package com.codegym.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_deleteEmployee {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteEmployee_25() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/admin/employee/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteEmployee_28() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/admin/employee/{id}", "4"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteEmployee_26() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/admin/employee/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteEmployee_27() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/admin/employee/{id}", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
