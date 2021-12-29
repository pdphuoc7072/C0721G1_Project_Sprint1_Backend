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
import java.util.Optional;


@RestController
@EnableWebMvc
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuppliesControllerCreate {
    //    ThanhCode 24-12
    @Autowired
    private ISuppliesService suppliesService;

    //Thanh 29/12
    @GetMapping("admin/supplies/list")
    public ResponseEntity<?> getSuppliesList() {
        List<Supplies> productList = (List<Supplies>) suppliesService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //Thanh 29/12
    @GetMapping("admin/supplies/code")
    public ResponseEntity<?> suppliesCode() {
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        Supplies count = suppliesList.get(suppliesList.size() - 1);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    //Thanh 29/12
    @GetMapping("/admin/supplies/findById/{id}")
    public ResponseEntity<?> getSupplies(@PathVariable Long id) {
        Optional<Supplies> supplies = suppliesService.findById(id);
        if (supplies.isPresent()) {
            return new ResponseEntity<>(supplies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Thanh 29/12
    @PostMapping("admin/supplies/create")
    public ResponseEntity<?> createSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult) {
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

    //Thanh 29/12
    @PatchMapping("admin/supplies/edit/{id}")
    public ResponseEntity<?> editSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult1) {
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        suppliesDTO.setSuppliesList(suppliesList);
        suppliesDTO.validate(suppliesDTO, bindingResult1);
        if (bindingResult1.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult1.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            Supplies supplies = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, supplies);
            supplies.setId(suppliesDTO.getId());
            suppliesService.save(supplies);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //Thanh 29/12
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
=======

import com.codegym.dto.PageSuppliesDTO;
import com.codegym.dto.SuppliesDTO;
import com.codegym.model.Producer;
import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import com.codegym.service.IProducerService;
import com.codegym.service.ISuppliesService;
import com.codegym.service.ISuppliesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/supplies")
@CrossOrigin
public class SuppliesController {
    @Autowired
    ISuppliesService iSuppliesService;
    @Autowired
    ISuppliesTypeService iSuppliesTypeService;
    @Autowired
    IProducerService iProducerService;

    //Huy
    @GetMapping("/suppliestype")
    public ResponseEntity<?> getSuppliesTypeList() {
        List<SuppliesType> suppliesTypeList = iSuppliesTypeService.getAll();
        return new ResponseEntity<>(suppliesTypeList, HttpStatus.OK);
    }

    //Huy
    @GetMapping("/producer")
    public ResponseEntity<?> getProducerList() {
        List<Producer> producerList = iProducerService.getAll();
        return new ResponseEntity<>(producerList, HttpStatus.OK);
    }

    //Huy
    @GetMapping("")
    public ResponseEntity<?> findAllSupplies(@RequestParam String code,
                                             @RequestParam String name,
                                             @RequestParam String suppliesType,
                                             @RequestParam int page,
                                             @RequestParam int size
    ) throws ParseException {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "code");

        Page<SuppliesDTO> suppliesPage = iSuppliesService.findAllSupplies(pageable, name, code, suppliesType);
        if (suppliesPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
    }

    //Huy
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        Supplies supplies = iSuppliesService.findById(id).get();
//        return new ResponseEntity<>(supplies, HttpStatus.OK);
//    }

    //Huy
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplies(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (iSuppliesService.existsByIdSupplies(id)) {
            iSuppliesService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
