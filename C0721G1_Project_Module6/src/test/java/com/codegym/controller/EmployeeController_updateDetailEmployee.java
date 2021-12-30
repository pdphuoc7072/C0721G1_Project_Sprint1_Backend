//package com.codegym.controller;
//
//
//import com.codegym.dto.EmployeeDto;
//
//import com.codegym.model.Position;
//import com.codegym.model.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class EmployeeController_updateDetailEmployee {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    //    All [item] đều hợp lệ
//    @Test
//    public void editDetail_Employee_24() throws Exception {
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
//        employeeDto.setCode("NV-0001");
//        employeeDto.setName("Nguyễn Văn Hai");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }
//
////    [item] = null
//
//    @Test
//    public void editDetail_Employee_19() throws Exception {
//
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
//        employeeDto.setCode("");
//        employeeDto.setName("Nguyễn Văn Hai");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//
////[item] =  rỗng ("")
//
//    @Test
//    public void editDetail_Employee_20() throws Exception {
//
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
////        employeeDto.setCode("");
//        employeeDto.setName("Nguyễn Văn Hai");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
////    [item] sai format (phụ thuộc vào từng item cụ thể: chỉ chứa chữ, chỉ chứa số, định dạng email…)
//
//    @Test
//    public void editDetail_Employee_21() throws Exception {
//
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
//        employeeDto.setCode("NV-1");
//        employeeDto.setName("Nguyễn Văn Hai");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
////    [item] không >= minlength
//
//    @Test
//    public void editDetail_Employee_22() throws Exception {
//
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
//        employeeDto.setCode("NV-0001");
//        employeeDto.setName("N");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    //    [item] không <= maxlength
//
//    @Test
//    public void editDetail_Employee_23() throws Exception {
//
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId((long) 1);
//        employeeDto.setCode("NV-0001");
//        employeeDto.setName("Nguyễn Văn Haigdfgreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//        employeeDto.setDateOfBirth("1990-10-10");
//        employeeDto.setImage("abc.png");
//        employeeDto.setGender(1);
//        employeeDto.setPhone("0904564564");
//        employeeDto.setAddress("08 Thi Sách");
//        Position position = new Position();
//        position.setId(1L);
//        position.setName("Manager");
//        employeeDto.setPosition(position);
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("hung");
//        user.setPassword("545454");
//        employeeDto.setUser(user);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .patch("/api/employee/detail/update")
//                        .content(this.objectMapper.writeValueAsString(employeeDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//
//}
//
//
