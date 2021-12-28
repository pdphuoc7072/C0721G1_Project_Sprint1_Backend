package com.codegym.controller;

import com.codegym.controllerEmployee.EmployeeController;
import com.codegym.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_findAllEmployee {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void findAllEmployee_5(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("","","", 3,2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void findAllEmployee_6(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("","","", 0,2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals("Huy", responseEntity.getBody().getContent().get(1).getName());
    }
    @Test
    public void findAllEmployee_6_1(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("","H","", 0,2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Hoa", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllEmployee_6_2(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("02","","", 0,2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Huy", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllEmployee_6_3(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("","","1", 0,2);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Hoa", responseEntity.getBody().getContent().get(0).getName());
    }

    @Test
    public void findAllEmployee_5_1(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("fnfhre","","", 3,2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void findAllEmployee_5_2(){
        ResponseEntity<Page<Employee>> responseEntity
                = (ResponseEntity<Page<Employee>>) this.employeeController.findAllEmployee("","fndfn","", 3,2);
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }
}
