package com.codegym.service;

import com.codegym.model.Employee;

import java.util.Optional;

public interface IEmployeeService extends IGenericService<Employee> {
    Optional<Employee> findByUserId(Long id);
}
