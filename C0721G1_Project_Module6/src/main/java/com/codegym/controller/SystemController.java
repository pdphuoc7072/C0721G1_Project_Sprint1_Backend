package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.Supplies;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RequestMapping("home")
@CrossOrigin
@RestController
public class SystemController {

    @Autowired
    IEmployeeService employeeService;
    @GetMapping("/system")
    public ResponseEntity<?> findAll() {
        Iterable<Employee> employeeList = employeeService.findAll();
        if (employeeList != null) {
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
