package com.example.statsticals.controller;

import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.service.IPotentialCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/stats/potential-customer")
@CrossOrigin
public class PotentialCustomerController {
    @Autowired
    IPotentialCustomerService iPotentialCustomerService;
    @GetMapping("")
    public ResponseEntity<?> getPotentialCustomerStats() {
        List<PotentialCustomerDto> potencialDtoList = iPotentialCustomerService.getAll();
        if(!potencialDtoList.isEmpty()){
            return new ResponseEntity<>(potencialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
