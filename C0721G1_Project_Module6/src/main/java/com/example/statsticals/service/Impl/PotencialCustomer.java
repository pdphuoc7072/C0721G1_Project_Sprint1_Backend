package com.example.statsticals.service.Impl;

import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.repository.PotentialCustomerRepository;
import com.example.statsticals.service.IPotentialCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotencialCustomer implements IPotentialCustomerService {
    @Autowired
    PotentialCustomerRepository potentialCustomerRepository;

    @Override
    public List<PotentialCustomerDto> getAll() {
        return potentialCustomerRepository.getAll();
    }
}
