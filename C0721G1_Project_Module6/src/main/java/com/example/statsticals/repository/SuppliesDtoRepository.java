package com.example.statsticals.repository;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliesDtoRepository extends JpaRepository<Warehouse, Long> {

     @Query(nativeQuery = true, value ="select s.`name`, s.`code`, w.import_quantity, o.quantity, w.normal_supplies, (w.refund_supplies + w.cancelled_supplies) as another\n" +
             "from warehouse w join supplies s on s.id = w.supplies_id\n" +
             "                 join order_detail o on s.id = o.id\n" +
             "group by s.`code`;")
     List<SuppiliesDtoInterface> getAll();
}
