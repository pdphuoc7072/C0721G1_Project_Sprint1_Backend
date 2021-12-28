package com.codegym.controller;

import com.example.statsticals.controller.FinancialController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FinancialListController_getListFinancial {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListFinancial_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/financial-stats"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFinancial_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/financial-stats"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.getIncome()").value("175615000"))
                .andExpect(jsonPath("$.getImport()").value("10375000"))
                .andExpect(jsonPath("$.getCancelled()").value("160000"));
    }


    public void getListFinancial_10() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/financial-stats/{date}","2021-12-21"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.getMonthSales").value("75000000"))
                .andExpect(jsonPath("$.getMonthImport").value("10375000"));
    }

    public void getListFinancial_09() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/financial-stats/{date}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    public void getListFinancial_08() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/financial-stats/{date}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    public void getListFinancial_07() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/financial-stats/{date}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
