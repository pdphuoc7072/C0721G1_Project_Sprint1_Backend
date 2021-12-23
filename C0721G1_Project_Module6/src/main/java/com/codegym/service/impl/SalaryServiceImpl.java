package com.codegym.service.impl;

import com.codegym.model.Salary;
import com.codegym.repository.ISalaryRepository;
import com.codegym.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private ISalaryRepository iSalaryRepository;

    @Override
    public Iterable<Salary> findAll() {
        return iSalaryRepository.findAll();
    }

    @Override
    public Optional<Salary> findById(Long id) {
        return iSalaryRepository.findById(id);
    }

    @Override
    public void save(Salary salary) {
        iSalaryRepository.save(salary);
    }

    @Override
    public void remove(Long id) {
        iSalaryRepository.deleteById(id);
    }
}
