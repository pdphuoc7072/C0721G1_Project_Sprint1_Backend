package com.codegym.repository;

import com.codegym.model.Supplies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISuppliesRepository extends JpaRepository<Supplies, Long> {

}
