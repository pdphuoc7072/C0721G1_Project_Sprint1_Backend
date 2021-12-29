package com.example.statsticals.service;

import com.example.statsticals.dto.SuppiliesDtoInterface;

import com.example.statsticals.dto.TrendingSupplies;

import java.time.LocalDate;



import java.util.List;

public interface ISuppliesService {
    List<SuppiliesDtoInterface> getAll();

    List<SuppiliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate);

    List<TrendingSupplies> getTrendingSupplies();

}
