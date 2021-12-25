package com.codegym.controllerEmployee;

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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class ControllerEmployee {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

    @GetMapping("/admin/employee")
    public ResponseEntity<?> findAllEmployee(@RequestBody PageEmployeeDTO pageEmployeeDTO) {
        String code = pageEmployeeDTO.getCode();
        String name = pageEmployeeDTO.getName();
        String positionId = pageEmployeeDTO.getPositionId();
        Pageable pageable = PageRequest.of(pageEmployeeDTO.getPage(), pageEmployeeDTO.getSize(), Sort.Direction.ASC,"name");

        Page<Employee> employeePage = employeeService.findAllEmployee(code, name , positionId, pageable);
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
        employeeService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/position")
    public ResponseEntity<?> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }
}
