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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin
class ControllerEmployee {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IPositionService positionService;

//    @GetMapping("/admin/employee")
//    public ResponseEntity<?> findAllEmployee(@RequestParam int page) {
//        return null;
//    }
//
//    @PostMapping("/admin/employee/create")
//    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
//        employeeService.save(employee);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @PatchMapping("/employee/update")
//    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
//        employeeService.save(employee);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/employee/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        Employee employee = employeeService.findById(id).get();
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/admin/employee/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
//        employeeService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/position")
//    public ResponseEntity<?> findAllPosition() {
//        List<Position> positionList = positionService.findAll();
//        return new ResponseEntity<>(positionList, HttpStatus.OK);
//    }

//    @PatchMapping("/employee/detail/update")
//    public ResponseEntity<?> updateDetailEmployeeById(@RequestBody Employee employee){
//        employeeService.save(employee);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PatchMapping("/employee/detail/update")
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


    @GetMapping("/employee/detail/{id}")
    public ResponseEntity<?> findDetailEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
