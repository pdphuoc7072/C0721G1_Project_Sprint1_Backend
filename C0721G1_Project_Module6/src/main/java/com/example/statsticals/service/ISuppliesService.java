package com.example.statsticals.service;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ISuppliesService {
    List<SuppiliesDtoInterface> getAll();
    List<SuppiliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate);
}
