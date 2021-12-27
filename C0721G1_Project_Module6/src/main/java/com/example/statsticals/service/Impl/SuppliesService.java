package com.example.statsticals.service.Impl;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.repository.SuppliesDtoRepository;
import com.example.statsticals.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SuppliesService implements ISuppliesService {
    @Autowired
    SuppliesDtoRepository suppliesDtoRepository;


    @Override
    public List<SuppiliesDtoInterface> getAll() {
        return suppliesDtoRepository.getAll();
    }

    @Override
    public List<SuppiliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate) {
        return suppliesDtoRepository.getSuppliesByTime(startDate,endDate);
    }
}
