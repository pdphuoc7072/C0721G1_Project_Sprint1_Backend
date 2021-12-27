package com.example.statsticals.controller;

import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.service.IPotentialCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/stats/potential-customer")
@CrossOrigin
public class PotentialCustomerController {
    @Autowired
    IPotentialCustomerService iPotentialCustomerService;
<<<<<<< HEAD

    @GetMapping("")
    public ResponseEntity<?> getPotentialCustomerStats() {
        List<PotentialCustomerDto> potencialDtoList = iPotentialCustomerService.getAll();
        if (!potencialDtoList.isEmpty()) {
=======
    @GetMapping("")
    public ResponseEntity<?> getPotentialCustomerStats() {
        List<PotentialCustomerDto> potencialDtoList = iPotentialCustomerService.getAll();
        if(!potencialDtoList.isEmpty()){
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
            return new ResponseEntity<>(potencialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
<<<<<<< HEAD

    @GetMapping("/fetch")
    public ResponseEntity<?> getPotentialCustomerByTime(@RequestParam String startDate,
                                                        @RequestParam String endDate) {
        LocalDate ld = LocalDate.parse(startDate);
        LocalDate ld1 = LocalDate.parse(endDate);
        List<PotentialCustomerDto> potencialDtoList = iPotentialCustomerService.getPotentialCustomerByTime(ld,ld1);
        if (!potencialDtoList.isEmpty()) {
            return new ResponseEntity<>(potencialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
=======
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
}
