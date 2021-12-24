package com.codegym.service.impl;

import com.codegym.model.Position;
import com.codegym.repository.IPositionRepository;
import com.codegym.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private IPositionRepository iPositionRepository;

    @Override
    public Iterable<Position> findAll() {
        return iPositionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return iPositionRepository.findById(id);
    }

    @Override
    public void save(Position position) {
        iPositionRepository.save(position);
    }

    @Override
    public void remove(Long id) {
        iPositionRepository.deleteById(id);
    }
}
