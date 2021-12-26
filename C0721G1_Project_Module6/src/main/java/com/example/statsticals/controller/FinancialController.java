package com.example.statsticals.controller;

import com.example.statsticals.dto.FinancialStatsDto;
import com.example.statsticals.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/stats/financial-stats")
@CrossOrigin
public class FinancialController {
    @Autowired
    IFinancialService financialService;

    @GetMapping("")
    public ResponseEntity<?> getFinancialStats() {
        FinancialStatsDto financialStatsDto = new FinancialStatsDto();
        financialStatsDto.setIncome(financialService.getIncome());
        financialStatsDto.setImportMoney(financialService.getImport());
        financialStatsDto.setCancelled(financialService.getCancelled());
        financialStatsDto.setReturnMoney(financialService.getReturn());
        financialStatsDto.setRefund(financialService.getRefund());
        return new ResponseEntity<>(financialStatsDto, HttpStatus.OK) ;
    }

}
