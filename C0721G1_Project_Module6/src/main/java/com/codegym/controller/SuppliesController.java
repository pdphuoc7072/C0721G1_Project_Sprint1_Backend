package com.codegym.controller;

import com.codegym.dto.SuppliesDTO;
import com.codegym.model.Supplies;
import com.codegym.service.ISuppliesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@EnableWebMvc
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuppliesController {
    //    ThanhCode 24-12
    @Autowired
    private ISuppliesService suppliesService;


    @GetMapping("admin/supplies/list")
    public ResponseEntity<?> getProductList() {
        List<Supplies> productList = (List<Supplies>) suppliesService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @GetMapping("admin/supplies/code")
    public ResponseEntity<?> EmployeeCode() {
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        Supplies count = suppliesList.get(suppliesList.size() - 1);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PostMapping("admin/supplies/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult) {
        List<Supplies> supplies = (List<Supplies>) suppliesService.findAll();
        suppliesDTO.setSuppliesList(supplies);
        suppliesDTO.validate(suppliesDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            long count = supplies.get(supplies.size() - 1).getId() + 1;
            String code = "MVT-" + count;
            suppliesDTO.setCode(code);
            Supplies employee = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, employee);
            suppliesService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping("admin/supplies/edit")
    public ResponseEntity<?> editSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult1) {
        suppliesDTO.setSuppliesList((List<Supplies>) suppliesService.findAll());
        suppliesDTO.validate(suppliesDTO, bindingResult1);
        if (bindingResult1.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Supplies supplies = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, supplies);
            supplies.setId(suppliesDTO.getId());
            suppliesService.save(supplies);
            return new ResponseEntity<>(HttpStatus.OK);
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
}