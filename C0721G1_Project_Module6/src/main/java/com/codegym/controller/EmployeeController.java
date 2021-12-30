package com.codegym.controller;

import com.codegym.dto.EmployeeDto;
import com.codegym.dto.PageEmployeeDTO;
import com.codegym.dto.PasswordDTO;
import com.codegym.dto.UserDto;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.model.User;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IPositionService;
import com.codegym.service.IUserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    Creator: SangDN
     */
    @GetMapping("/admin/employee")
    public ResponseEntity<Page<Employee>> findAllEmployee(@RequestParam String code,
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

    /*
    Creator: SangDN
     */
    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
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
    public ResponseEntity<List<Position>> findAllPosition() {
        List<Position> positionList = positionService.findAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @PostMapping("/admin/employee/create")
    public ResponseEntity<HttpStatus> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            long count;
            String code;
            if (employees.isEmpty()) {
                code = "MNV-" + 1;
            } else {
                count = employees.get(employees.size() - 1).getId() + 1;
                code = "MNV-" + count;
            }
            employeeDto.setCode(code);
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /*
    Đức
     */
    @PatchMapping("/admin/employee/update")
    public ResponseEntity<HttpStatus> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        List<Employee> employees = employeeService.getAll();
        employeeDto.setEmployeeList(employees);
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /*
    Đức
     */
    @GetMapping("/admin/employee/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/admin/employee/code")
    public ResponseEntity<Employee> EmployeeCode() {
        List<Employee> employeeList = employeeService.getAll();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            Employee employee = employeeList.get(employeeList.size() - 1);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }

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

    /*
    Tính
     */
    @GetMapping("/user/employee/detail/{id}")
    public ResponseEntity<Employee> findDetailEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*
    Tính
     */
    @PatchMapping("/user/editPass/{oldPass}")
    public ResponseEntity<HttpStatus> editPassword(@PathVariable String oldPass, @RequestBody UserDto userDto) {
        String oldPassEncoder = passwordEncoder.encode(oldPass);
        Optional<User> oldUser = iUserService.findById(userDto.getId());
        if (oldPassEncoder.equals(oldUser.get().getPassword())) {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            iUserService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    /*
    Tính
     */
    @GetMapping("/user/find/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        Optional<User> user = this.iUserService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/user/change-password/{id}/{oldPassword}/{newPassword}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @PathVariable String oldPassword, @PathVariable String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return ResponseEntity.ok(1);
        }
        Optional<Employee> employeeCurrent = employeeService.findById(id);
        if (employeeCurrent.isPresent()) {
            if (passwordEncoder.matches(oldPassword, employeeCurrent.get().getUser().getPassword())) {
                employeeCurrent.get().getUser().setPassword(passwordEncoder.encode(newPassword));
                iUserService.save(employeeCurrent.get().getUser());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
