package com.example.statsticals.dto;

public class FinancialByMonthDto {
    private Integer getMonthSales;

    private Integer getMonthImport;

    public FinancialByMonthDto() {
    }

    public FinancialByMonthDto(Integer getMonthSales, Integer getMonthImport) {
        this.getMonthSales = getMonthSales;
        this.getMonthImport = getMonthImport;
    }

    public Integer getGetMonthSales() {
        return getMonthSales;
    }

    public void setGetMonthSales(Integer getMonthSales) {
        this.getMonthSales = getMonthSales;
    }

    public Integer getGetMonthImport() {
        return getMonthImport;
    }

    public void setGetMonthImport(Integer getMonthImport) {
        this.getMonthImport = getMonthImport;
    }
}
