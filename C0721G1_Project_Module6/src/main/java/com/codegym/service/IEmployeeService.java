package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService extends IGenericService<Employee> {
    Page<Employee> findAllEmployee(String code, String name,String positionId, Pageable pageable);
    boolean existsByIdEmployee(Long id);
}
