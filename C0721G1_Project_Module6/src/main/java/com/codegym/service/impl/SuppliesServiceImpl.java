package com.codegym.service.impl;

import com.codegym.model.Supplies;
import com.codegym.repository.ISuppliesRepository;
import com.codegym.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuppliesServiceImpl implements ISuppliesService {
    @Autowired
    private ISuppliesRepository iSuppliesRepository;

    @Override
    public Iterable<Supplies> findAll() {
        return iSuppliesRepository.findAll();
    }

    @Override
    public Optional<Supplies> findById(Long id) {
        return iSuppliesRepository.findById(id);
    }

    @Override
    public void save(Supplies supplies) {
        iSuppliesRepository.save(supplies);
    }

    @Override
    public void remove(Long id) {
        iSuppliesRepository.deleteById(id);
    }
}
