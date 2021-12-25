package com.codegym.service;

import com.codegym.model.Supplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISuppliesService extends IGenericService<Supplies> {
    Page<Supplies> findAll(Pageable pageable);
}
