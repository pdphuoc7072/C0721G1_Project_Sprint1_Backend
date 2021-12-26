package com.codegym.controller;


import com.codegym.dto.PageSuppliesDTO;
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


    @GetMapping("/suppliestype")
    public ResponseEntity<?> getSuppliesTypeList() {
        List<SuppliesType> suppliesTypeList = iSuppliesTypeService.getAll();
        return new ResponseEntity<>(suppliesTypeList, HttpStatus.OK);
    }

    @GetMapping("/producer")
    public ResponseEntity<?> getProducerList() {
        List<Producer> producerList = iProducerService.getAll();
        return new ResponseEntity<>(producerList, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<?> findAllSupplies(@RequestBody PageSuppliesDTO pageSuppliesDTO) {
        String code = pageSuppliesDTO.getCode();
        String name = pageSuppliesDTO.getName();
        String suppliesTypeId = pageSuppliesDTO.getSuppliesTypeId();
        Pageable pageable = PageRequest.of(pageSuppliesDTO.getPage(), pageSuppliesDTO.getSize(), Sort.Direction.ASC, "name");

        Page<Supplies> suppliesPage = iSuppliesService.findAllSupplies(pageable, name, code, suppliesTypeId);
        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Supplies supplies = iSuppliesService.findById(id).get();
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplies(@PathVariable Long id) {
        iSuppliesService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
