package com.example.statsticals.service;

import com.example.statsticals.dto.PotentialCustomerDto;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPotentialCustomerService {
    List<PotentialCustomerDto> getAll();
    List<PotentialCustomerDto> getPotentialCustomerByTime(LocalDate startDate, LocalDate endDate);
}
