package com.codegym.repository;

import com.codegym.model.Supplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISuppliesRepository extends JpaRepository<Supplies, Long> {
}
