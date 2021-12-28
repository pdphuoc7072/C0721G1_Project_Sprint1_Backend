package com.codegym.service;

import com.codegym.dto.SuppliesDTO;

import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface ISuppliesService extends IGenericService<Supplies> {
    Page<SuppliesDTO> findAllSupplies(Pageable pageable, String name, String code, String supplies_type_id) throws ParseException;

    List<Supplies> findAll();

    boolean existsByIdSupplies(Long id);
}
