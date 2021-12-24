package com.codegym.controllerEmployee;

import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
class ContronllerEmployee {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

    @GetMapping("/admin/employee")
    public ResponseEntity<?> findAllEmployee(@RequestParam int page) {
        return null;
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
