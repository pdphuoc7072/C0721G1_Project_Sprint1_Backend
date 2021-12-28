package com.codegym.controller;

import com.codegym.dto.PageEmployeeDTO;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "false")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

    @GetMapping("/admin/employee")
    public ResponseEntity<?> findAllEmployee(@RequestParam String code,
                                             @RequestParam String name,
                                             @RequestParam String positionId,
                                             @RequestParam int page,
                                             @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"name");

        Page<Employee> employeePage = employeeService.findAllEmployee(code, name , positionId, pageable);
        if(employeePage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }

    @PostMapping("/admin/employee/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/employee/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).get();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(employeeService.existsByIdEmployee(id)) {
            employeeService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/position")
    public ResponseEntity<?> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }
}
