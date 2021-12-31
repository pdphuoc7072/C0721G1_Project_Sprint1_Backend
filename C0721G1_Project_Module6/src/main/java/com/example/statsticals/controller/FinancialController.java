package com.example.statsticals.controller;

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
    public ResponseEntity<FinancialStatsDto> getFinancialStats() {
        FinancialStatsDto financialStatsDto = new FinancialStatsDto();
        financialStatsDto.setIncome(financialService.getIncome());
        financialStatsDto.setImportMoney(financialService.getImport());
        financialStatsDto.setCancelled(financialService.getCancelled());
        financialStatsDto.setReturnMoney(financialService.getReturn());
        financialStatsDto.setRefund(financialService.getRefund());

        if (financialStatsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(financialStatsDto, HttpStatus.OK);
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<FinancialStatsDto> getFinancialStatsByTime(@PathVariable String date) {

        String[] str = date.split("-");
        String newDate = str[0] + "-" + str[1];

        FinancialStatsDto financialStatsDto = new FinancialStatsDto();
        financialStatsDto.setIncome(financialService.getMonthSales(newDate));
        financialStatsDto.setImportMoney(financialService.getMonthImport(newDate));
        financialStatsDto.setRefund(financialService.getMonthRefund(newDate));
        financialStatsDto.setCancelled(financialService.getMonthCancelled(newDate));
        financialStatsDto.setReturnMoney(financialService.getMonthReturn(newDate));
        if (financialStatsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(financialStatsDto, HttpStatus.OK);
        }
    }
}
