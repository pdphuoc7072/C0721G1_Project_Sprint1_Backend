package com.example.statsticals.service;

import com.example.statsticals.dto.PotentialCustomerDto;

import java.util.List;

public interface IPotentialCustomerService {
    List<PotentialCustomerDto> getAll();
}
