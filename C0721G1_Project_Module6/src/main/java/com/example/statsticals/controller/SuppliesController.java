package com.example.statsticals.controller;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.dto.TrendingSupplies;
import com.example.statsticals.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/stats/supplies-stats")
@CrossOrigin
public class SuppliesController {
    @Autowired
    ISuppliesService iSuppliesService;

    @GetMapping("")
    public ResponseEntity<List<SuppiliesDtoInterface>> getSuppliesStats() {
        List<SuppiliesDtoInterface> suppiliesDtoInterfaceList = iSuppliesService.getAll();
        if (!suppiliesDtoInterfaceList.isEmpty()) {
            return new ResponseEntity<>(suppiliesDtoInterfaceList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<SuppiliesDtoInterface>> getPotentialCustomerByTime(@RequestParam String startDate,
                                                        @RequestParam String endDate) {
        LocalDate ld = LocalDate.parse(startDate);
        LocalDate ld1 = LocalDate.parse(endDate);
        List<SuppiliesDtoInterface> suppiliesDtoInterfaceList = iSuppliesService.getSuppliesByTime(ld, ld1);
        if (!suppiliesDtoInterfaceList.isEmpty()) {
            return new ResponseEntity<>(suppiliesDtoInterfaceList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/trending-supplies")
    public ResponseEntity<List<TrendingSupplies>> getTrendingSupplies(){
        List<TrendingSupplies> trendingSupplies = iSuppliesService.getTrendingSupplies();
        if(!trendingSupplies.isEmpty()){
            return new ResponseEntity<>(trendingSupplies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

