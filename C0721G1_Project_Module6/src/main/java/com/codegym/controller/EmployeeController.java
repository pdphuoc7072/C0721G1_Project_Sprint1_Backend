package com.codegym.controller;

import com.codegym.dto.EmployeeDto;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            String name = WordUtils.capitalizeFully(employeeDto.getName()).replaceAll("\\s+", " ");
            String address = WordUtils.capitalizeFully(employeeDto.getAddress()).replaceAll("\\s+", " ");
            employeeDto.setName(name);
            employeeDto.setName(address);
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
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
            String name = WordUtils.capitalizeFully(employeeDto.getName()).replaceAll("\\s+", " ");
            String address = WordUtils.capitalizeFully(employeeDto.getAddress()).replaceAll("\\s+", " ");
            employeeDto.setName(name);
            employeeDto.setName(address);
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //duc
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>("Không tìm thấy dữ liệu", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
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
    public ResponseEntity<?> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    //hung
    @PatchMapping("employee/edit-detail/update/{id}")
    public ResponseEntity<?> updateDetailEmployee(@Valid @RequestBody EmployeeDto employeeDTO, BindingResult bindingResult1) {
        if (bindingResult1.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setId(employeeDTO.getId());
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //hung
    @GetMapping("employee/edit-detail/{id}")
    public ResponseEntity<?> searchDetailEmployeeById(@PathVariable(name = "id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>("No data search", HttpStatus.BAD_REQUEST);
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

    //    list DucNV
    @GetMapping("/admin/employee/list")
    public ResponseEntity<?> findAllEmployeeList() {
        List<Employee> employeeList = employeeService.getAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    //duc
    @GetMapping("/admin/employee/code")
    public ResponseEntity<?> employeeCode() {
        List<Employee> employeeList = employeeService.getAll();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            Employee employee = employeeList.get(employeeList.size() - 1);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
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
