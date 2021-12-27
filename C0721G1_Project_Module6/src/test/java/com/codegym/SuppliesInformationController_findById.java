package com.codegym;

import com.codegym.dto.AddressDTO;
import com.codegym.dto.CustomerDTO;
import com.codegym.model.Address;
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
public class SuppliesInformationController_findById {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findById_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/home/list/detail/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code").value("MVT-0001"))
                .andExpect(jsonPath("$.expiryDate").value("2025-12-31"))
                .andExpect(jsonPath("$.image").value("https://i.imgur.com/e1Zqjwu.jpg"))
                .andExpect(jsonPath("$.introduce").value("ABCXYZ"))
                .andExpect(jsonPath("$.name").value("KHẨU TRANG THƯỜNG 3 & 4 LỚP"))
                .andExpect(jsonPath("$.price").value("50000"))
                .andExpect(jsonPath("$.productionDate").value("2022-01-01"))
                .andExpect(jsonPath("$.technicalInformation").value("XYZ"))
                .andExpect(jsonPath("$.producer.id").value(1))
                .andExpect(jsonPath("$.suppliesType.id").value(1));
    }
    @Test
    public void findById_1() throws  Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.  get("/home/list/detail/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void findById_2() throws  Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.  get("/home/list/detail/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void findById_3() throws  Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/home/list/detail/{id}", "9999"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

}
