package com.example.statsticals.service.Impl;

import com.example.statsticals.dto.*;
import com.example.statsticals.repository.FinancialDtoRepository;
import com.example.statsticals.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService implements IFinancialService {
    @Autowired
    private FinancialDtoRepository financialDtoRepository;




    @Override
    public Integer getIncome() {
        return financialDtoRepository.getIncome();
    }

    @Override
    public Integer getReturn() {
        return financialDtoRepository.getReturn();
    }

    @Override
    public Integer getImport() {
        return financialDtoRepository.getImport();
    }

    @Override
    public Integer getCancelled() {
        return financialDtoRepository.getCancelled();
    }

    @Override
    public Integer getRefund() {
        return financialDtoRepository.getRefund();
    }

    @Override
    public Integer getMonthSales(String date) {
        return financialDtoRepository.getMonthSales(date + "%");
    }

    @Override
    public Integer getMonthImport(String date) {
        return financialDtoRepository.getMonthImport(date + "%");
    }

}
