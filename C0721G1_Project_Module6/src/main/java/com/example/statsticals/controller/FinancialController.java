package com.example.statsticals.controller;

import com.example.statsticals.dto.FinancialByMonthDto;
import com.example.statsticals.dto.FinancialStatsDto;
import com.example.statsticals.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if (financialStatsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(financialStatsDto, HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getFinancialStatsByTime(@PathVariable String date) {
        String[] str = date.split("-");
        String newDate = str[0]+"-"+str[1];
        FinancialByMonthDto financialByMonthDto = new FinancialByMonthDto(
                financialService.getMonthSales(newDate),
                financialService.getMonthImport(newDate));
        if(!(financialByMonthDto.getGetMonthSales() == 0)){
            return new ResponseEntity<>(financialByMonthDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
