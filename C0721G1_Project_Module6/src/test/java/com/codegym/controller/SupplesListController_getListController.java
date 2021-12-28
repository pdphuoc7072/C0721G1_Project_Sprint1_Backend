package com.codegym.controller;

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
public class SupplesListController_getListController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListSupplies_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/supplies-stats"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFinancial_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/supplies-stats"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())

                .andExpect(jsonPath("$.getCode()").value("Thuốc lào"))
                .andExpect(jsonPath("$.getName()").value("MVT-001"))
                .andExpect(jsonPath("$.import_quantity").value("5000"));
    }
    @Test
    public void getListFinancial_10() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080//api/admin/stats/supplies-stats/fetch?","startDate=2021-11-11&endDate=2021-12-12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.getCode()").value("Thuốc lào"))
                .andExpect(jsonPath("$.getName()").value("MVT-001"));
    }
    @Test
    public void getListFinancial_09() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080//api/admin/stats/supplies-stats/fetch?", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void getListFinancial_08() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080//api/admin/stats/supplies-stats/fetch?", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.name").value("Thuốc lào"))
                .andExpect(jsonPath("$.code").value("MVT-001"))
                .andExpect(jsonPath("$.import_quantity").value("5000"));
    }

}