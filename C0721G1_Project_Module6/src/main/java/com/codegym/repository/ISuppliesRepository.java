package com.codegym.repository;

import com.codegym.model.Supplies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISuppliesRepository extends JpaRepository<Supplies, Long> {
    @Query(value="select * from supplies where `name` like :name and code like :code and supplies_type_id like :supplies_type_id and substring_index(expiry_date,'-',-1) <= 2020",nativeQuery=true)
    Page<Supplies> findAllSuppliesOld(Pageable pageable, @Param("name") String name,@Param("code") String code,  @Param("supplies_type_id") String supplies_type_id );
    @Query(value="select * from supplies where `name` like :name and code like :code and supplies_type_id like :supplies_type_id and substring_index(expiry_date,'-',-1) > 2020",nativeQuery=true)
    Page<Supplies> findAllSuppliesNew(Pageable pageable, @Param("name") String name,@Param("code") String code,  @Param("supplies_type_id") String supplies_type_id );

}
