package com.codegym.service;


import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.util.Optional;

import java.util.List;

public interface IEmployeeService extends IGenericService<Employee> {

    List<Employee> getAll();

    Page<Employee> findAllEmployee(String code, String name,String positionId, Pageable pageable);
    boolean existsByIdEmployee(Long id);



}

