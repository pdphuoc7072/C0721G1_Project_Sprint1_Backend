package com.codegym.controller;


import com.codegym.dto.EmployeeDto;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api")
@CrossOrigin

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

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        Page<Employee> employeePage = employeeService.findAllEmployee(code, name, positionId, pageable);
        if (employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }


    //    duc
    @PostMapping("/admin/employee/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            employeeDto.setCode(getCode());
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //duc
    @GetMapping("/admin/employee/code")
    public ResponseEntity<Employee> getEmployeeCode() {
        Employee employeeC = new Employee();
        employeeC.setCode(getCode());
        return new ResponseEntity<>(employeeC, HttpStatus.OK);
    }

    private String getCode() {
        String code = "MNV-";
        List<Integer> codeList = new ArrayList<>();
        List<Employee> employeeList = employeeService.getAll();
        if (employeeList.isEmpty()) {
            return ("MNV-0001");
        } else {
            for (Employee employee : employeeList) {
                String[] arrayCode = employee.getCode().split("-");
                codeList.add(Integer.parseInt(arrayCode[1]));
            }
            Collections.sort(codeList);
            int index = 0;
            for (int i = 0; i < codeList.size(); i++) {
                if (i == codeList.size() - 1) {
                    index = codeList.size();
                    break;
                }
                if (codeList.get(i + 1) - codeList.get(i) >= 2) {
                    index = i + 1;
                    break;
                }
            }
            if (index > 999) {
                code += (index + 1);
            } else if (index > 99) {
                code += "0" + (index + 1);
            } else if (index > 9) {
                code += "00" + (index + 1);
            } else if (index > 0) {
                code += "000" + (index + 1);
            }
            return (code);
        }
    }


    //    duc
    @PatchMapping("/employee/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //duc
    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (employeeService.existsByIdEmployee(id)) {
            employeeService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/position")
    public ResponseEntity<List<Position>> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    //bat err >>duc
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // TinhBt
    @GetMapping("/employee/detail/{id}")
    public ResponseEntity<?> findDetailEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}