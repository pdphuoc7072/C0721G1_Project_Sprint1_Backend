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

import java.time.LocalDateTime;
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


//    @GetMapping("/")
//    public ResponseEntity<?> findAllSuppliesOld(@RequestBody PageSuppliesDTO pageSuppliesDTO) {
//        String code = pageSuppliesDTO.getCode();
//        String name = pageSuppliesDTO.getName();
//        String suppliesTypeId = pageSuppliesDTO.getSuppliesTypeId();
//        Pageable pageable = PageRequest.of(pageSuppliesDTO.getPage(), pageSuppliesDTO.getSize(), Sort.Direction.ASC, "name");
//
//        Page<Supplies> suppliesPage = iSuppliesService.findAllSuppliesOld(pageable, name, code, suppliesTypeId);
//        for (int i = 0; i < suppliesPage.getContent().size(); i++) {
//
//            String[] string = suppliesPage.getContent().get(i).getExpiryDate().split("-");
//            LocalDateTime localDateTime = LocalDateTime.now();
//            int year = localDateTime.getYear() - 1;
//            if (Integer.parseInt(string[2]) < year) {
//                suppliesPage.getContent().get(i).getStatus() = 0
//            }
//        }
//        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
//    }

    @GetMapping("/old")
    public ResponseEntity<?> findAllSuppliesOld(@RequestBody PageSuppliesDTO pageSuppliesDTO) {
        String code = pageSuppliesDTO.getCode();
        String name = pageSuppliesDTO.getName();
        String suppliesTypeId = pageSuppliesDTO.getSuppliesTypeId();
        Pageable pageable = PageRequest.of(pageSuppliesDTO.getPage(), pageSuppliesDTO.getSize(), Sort.Direction.ASC, "name");

        Page<Supplies> suppliesPage = iSuppliesService.findAllSuppliesOld(pageable, name, code, suppliesTypeId);
        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<?> findAllSuppliesNew(@RequestBody PageSuppliesDTO pageSuppliesDTO) {
        String code = pageSuppliesDTO.getCode();
        String name = pageSuppliesDTO.getName();
        String suppliesTypeId = pageSuppliesDTO.getSuppliesTypeId();
        Pageable pageable = PageRequest.of(pageSuppliesDTO.getPage(), pageSuppliesDTO.getSize(), Sort.Direction.ASC, "name");

        Page<Supplies> suppliesPage = iSuppliesService.findAllSuppliesNew(pageable, name, code, suppliesTypeId);
        return new ResponseEntity<>(suppliesPage, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Supplies supplies = iSuppliesService.findById(id).get();
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplies(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iSuppliesService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
