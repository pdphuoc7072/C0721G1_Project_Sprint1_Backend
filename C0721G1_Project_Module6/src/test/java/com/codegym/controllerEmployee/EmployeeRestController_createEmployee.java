package com.codegym.controllerEmployee;


import com.codegym.dto.EmployeeDto;
import com.codegym.model.Position;
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
public class EmployeeRestController_createEmployee {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createEmployee_name_13() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(null);
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_birthDay_13() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday(null);
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_phone_13() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone(null);
        employeeDto.setBirthday("2000-01-01");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_address_13() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-05-03");
        employeeDto.setGender(0);
        employeeDto.setAddress(null);
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createEmployee_name_14() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_phone_14() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("");
        employeeDto.setBirthday("2000-01-01");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_address_14() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-05-03");
        employeeDto.setGender(0);
        employeeDto.setAddress("");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_birthDay_14() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_name_15() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyen van 123");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_birthDay_15() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000/01/01");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_phone_15() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyễn văn a");
        employeeDto.setPhone("0985666777");
        employeeDto.setBirthday("2000-01-01");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_name_16() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("a");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_name_17() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyen van aaaaaaaaaaaaaaaaaaaaaaaaa");
        employeeDto.setPhone("0909999111");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void createEmployee_name_18() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Nguyen van a");
        employeeDto.setPhone("0904445556");
        employeeDto.setBirthday("2000-10-16");
        employeeDto.setGender(0);
        employeeDto.setAddress("Huế");
        employeeDto.setImage("anh1.png");
        Position position = new Position();
        position.setId((long) 1);
        position.setName("Quản lý");
        employeeDto.setPosition(position);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/employee/create")
                .content(this.objectMapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
