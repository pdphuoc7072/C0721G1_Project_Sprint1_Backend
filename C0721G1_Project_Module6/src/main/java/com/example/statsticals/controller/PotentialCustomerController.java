package com.example.statsticals.controller;

import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.service.IPotentialCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        if (!potencialDtoList.isEmpty()) {
            return new ResponseEntity<>(potencialDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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
}
