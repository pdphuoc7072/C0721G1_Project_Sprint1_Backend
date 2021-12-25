package com.codegym.repository;

import com.codegym.model.Employee;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(Long id);
    Optional<Employee> findByCode(String code);
}
