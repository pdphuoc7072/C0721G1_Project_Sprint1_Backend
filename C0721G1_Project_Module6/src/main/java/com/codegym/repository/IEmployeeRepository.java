package com.codegym.repository;

import com.codegym.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
