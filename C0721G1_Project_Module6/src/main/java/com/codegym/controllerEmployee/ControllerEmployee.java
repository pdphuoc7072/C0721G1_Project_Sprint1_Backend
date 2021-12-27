package com.codegym.controllerEmployee;

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
import org.apache.commons.lang3.text.WordUtils;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin
public class ControllerEmployee {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

    @GetMapping("/admin/employee")
    public ResponseEntity<?> findAllEmployee(@RequestParam int page) {
        return null;
    }


    //    duc
    @PostMapping("/admin/employee/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
//        for (Employee e : employees) {
//            if (employeeDto.getPhone().equals(e.getPhone())) {
//                return new ResponseEntity<>("trùng số điện thoại", HttpStatus.BAD_REQUEST);
//            }
//        }
        else {
            String name = WordUtils.capitalizeFully(employeeDto.getName()).replaceAll("\\s+", " ");
            long count = employees.get(employees.size() - 1).getId() + 1;
            String code = "Emp-" + count;
            employeeDto.setCode(code);
            employeeDto.setName(name);
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
            employeeDto.setName(name);
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
        employeeService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/position")
    public ResponseEntity<?> findAllPosition() {
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
//    list DucNV
    @GetMapping("/admin/employee/list")
    public ResponseEntity<?> findAllEmployeeList() {
        List<Employee> employeeList = employeeService.getAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}