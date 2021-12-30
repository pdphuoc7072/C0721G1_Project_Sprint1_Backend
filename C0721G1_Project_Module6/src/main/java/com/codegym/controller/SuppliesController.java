package com.codegym.controller;

import com.codegym.dto.SuppliesDTO;
import com.codegym.model.Supplies;
import com.codegym.service.ISuppliesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.*;


@RestController
@EnableWebMvc
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuppliesController {
    //    ThanhCode 24-12
    @Autowired
    private ISuppliesService suppliesService;

    //Thanh 29/12
    @GetMapping("admin/supplies/list")
    public ResponseEntity<List<Supplies>> getSuppliesList() {
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        return new ResponseEntity<>(suppliesList, HttpStatus.OK);
    }

    //Thanh 29/12
    @GetMapping("admin/supplies/code")
    public ResponseEntity<?> suppliesCode() {
      Supplies supplies = new Supplies();
      supplies.setCode(getCode());
        return new ResponseEntity<>(supplies, HttpStatus.OK);
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
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        } else {
            suppliesDTO.setCode(getCode());
            Supplies supplies1 = new Supplies();
            BeanUtils.copyProperties(suppliesDTO, supplies1);
            suppliesService.save(supplies1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    private String getCode() {
        String code = "MVT-";
        List<Integer> codeList = new ArrayList<>();
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        for (Supplies supplies : suppliesList) {
            String[] arrayCode = supplies.getCode().split("-");
            codeList.add(Integer.parseInt(arrayCode[1]));
        }
        Collections.sort(codeList);
        int index = 0;
        for (int i = 0; i < codeList.size(); i++) {
            if (i == codeList.size()-1) {
                index = codeList.size();
                break;
            }
            if (codeList.get(i + 1) - codeList.get(i) >= 2) {
                index = i + 1;
                break;
            }
        }
        if (index > 998) {
            return code+= (index + 1);
        }
        if (index > 98) {
            return code += "0" + (index + 1);
        }
        if (index > 8) {
            return code += "00" + (index + 1);
        }
        if (index > 0) {
            return code += "000" + (index + 1);
        }
        return code;
    }
    //Thanh 29/12
    @PatchMapping("admin/supplies/edit")
    public ResponseEntity<?> editSupplies(@Valid @RequestBody SuppliesDTO suppliesDTO, BindingResult bindingResult1) {
        List<Supplies> suppliesList = (List<Supplies>) suppliesService.findAll();
        suppliesDTO.setSuppliesList(suppliesList);
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
}
