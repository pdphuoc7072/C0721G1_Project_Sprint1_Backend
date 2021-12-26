package com.codegym.service;

import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISuppliesService extends IGenericService<Supplies> {
    Page<Supplies> findAllSupplies(Pageable pageable, String name, String code,String suppliesTypeId );

}
