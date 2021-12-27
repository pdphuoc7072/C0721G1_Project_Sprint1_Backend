package com.codegym;

import com.codegym.dto.AddressDTO;
import com.codegym.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SuppliesInformationController_createCustomer {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCustomer_13() throws  Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createCustomer_14() throws  Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(""))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_15() throws  Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Ngux534fsd");
        customerDTO.setPhone("094abc77888");
        customerDTO.setEmail("nhat@@@@@quang@gmail.com");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId((long)1);
        customerDTO.setAddress(addressDTO);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void createCustomer_16() throws  Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Nguyễn Quang Nhật");
        customerDTO.setPhone("077888");
        customerDTO.setEmail("nhatquang@gmail.com");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId((long)1);
        customerDTO.setAddress(addressDTO);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void createCustomer_17() throws  Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Nguyễn Quang Nhật");
        customerDTO.setPhone("077888123123123");
        customerDTO.setEmail("nhatquang@gmail.com");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId((long)1);
        customerDTO.setAddress(addressDTO);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void createCustomer_18() throws  Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Nguyễn Thanh");
        customerDTO.setPhone("0909999888");
        customerDTO.setEmail("thanhnguyen@gmail.com");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId((long)1);
        customerDTO.setAddress(addressDTO);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/home/payment/")
                        .content(this.objectMapper.writeValueAsString(customerDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
