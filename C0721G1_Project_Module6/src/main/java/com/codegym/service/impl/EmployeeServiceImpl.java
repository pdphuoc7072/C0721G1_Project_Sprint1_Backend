package com.codegym.service.impl;

import com.codegym.model.Employee;
import com.codegym.repository.IEmployeeRepository;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void remove(Long id) {
        iEmployeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> getAll() {
        return iEmployeeRepository.findAll();
    }


    @Override
    public Page<Employee> findAllEmployee(String code, String name, String positionId, Pageable pageable) {
        return iEmployeeRepository.findAllEmployee("%" + code + "%", "%" + name + "%","%" + positionId + "%",  pageable);
    }

    @Override
    public boolean existsByIdEmployee(Long id) {
      return iEmployeeRepository.existsById(id);
    }

}
