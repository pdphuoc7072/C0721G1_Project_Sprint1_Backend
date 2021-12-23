package com.codegym.repository;

import com.codegym.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryRepository extends JpaRepository<Salary, Long> {
}
