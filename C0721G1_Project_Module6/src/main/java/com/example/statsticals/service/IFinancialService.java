package com.example.statsticals.service;

import com.example.statsticals.dto.*;

import java.util.List;

public interface IFinancialService {
    Integer getIncome();
    Integer getReturn();
    Integer getImport();
    Integer getCancelled();
    Integer getRefund();
    Integer getMonthSales(String date);
    Integer getMonthImport(String date);
    Integer getMonthReturn(String date);
    Integer getMonthRefund(String date);
    Integer getMonthCancelled(String date);

}
