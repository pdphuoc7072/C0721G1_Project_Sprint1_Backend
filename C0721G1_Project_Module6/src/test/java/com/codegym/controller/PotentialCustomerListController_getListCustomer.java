package com.codegym.controller;

import com.example.statsticals.controller.PotentialCustomerController;
import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.service.IPotentialCustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class PotentialCustomerListController_getListCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListCustomer_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/potential-customer"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFinancial_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/admin/stats/potential-customer"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Huy Gia"))
                .andExpect(jsonPath("$.code").value("KH-003"))
                .andExpect(jsonPath("$.total").value("15000000"));
    }

    @Test
    public void getListFinancial_10() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/potential-customer/fetch?","startDate=2021-11-11&endDate=2021-12-12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.getCode()").value("Tan Dep Trai"))
                .andExpect(jsonPath("$.getName()").value("KH-001"));
    }
    @Test
    public void getListFinancial_09() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/potential-customer/fetch?", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void getListFinancial_08() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/admin/stats/potential-customer/fetch?", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}


