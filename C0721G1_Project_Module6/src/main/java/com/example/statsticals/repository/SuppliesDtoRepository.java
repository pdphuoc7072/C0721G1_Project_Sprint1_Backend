package com.example.statsticals.repository;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.dto.TrendingSupplies;
import com.example.statsticals.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


import java.util.List;

@Repository
public interface SuppliesDtoRepository extends JpaRepository<Warehouse, Long> {

     @Query(nativeQuery = true, value ="select s.`name`, s.`code`, w.import_quantity, o.quantity, w.normal_supplies, (w.refund_supplies + w.cancelled_supplies) as another\n" +
             "from warehouse w join supplies s on s.id = w.supplies_id\n" +
             "                 join order_detail o on s.id = o.id\n" +
             "group by s.`code`;")
     List<SuppiliesDtoInterface> getAll();


    @Query(nativeQuery = true, value ="select s.`name`, s.`code`, w.import_quantity, o.quantity, o.order_date, w.normal_supplies, (w.refund_supplies + w.cancelled_supplies) as another\n" +
            "from warehouse w join supplies s on s.id = w.supplies_id\n" +
            "                 join order_detail o on s.id = o.id\n" +
            "where o.order_date between :startDate and :endDate\n\n" +
            "group by s.`code`;")
    List<SuppiliesDtoInterface> getSuppliesByTime(@Param("startDate") LocalDate date, @Param("endDate") LocalDate date2);


    @Query(nativeQuery =true, value = "select SUM(o.quantity) as quantity, s.id , s.name, s.image, s.introduce\n" +
            "from order_detail o join supplies s on s.id = o.supplies_id\n" +
            "group by s.id\n" +
            "order by o.quantity desc\n" +
            "limit 0,5")
    List<TrendingSupplies> getTrendingSupplies();
}
