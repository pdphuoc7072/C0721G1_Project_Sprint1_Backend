package com.example.statsticals.repository;

import com.example.statsticals.dto.PotentialCustomerDto;
import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PotentialCustomerRepository extends JpaRepository<Warehouse, Long> {
    @Query(nativeQuery = true, value ="select c.`code`,c.`name`,o.quantity, s.price*o.quantity as total\n" +
            "from customer c join order_detail o on c.id = o.customer_id\n" +
            "                join supplies s on s.id = o.supplies_id\n" +
            "group by c.`code`\n" +
            "order by o.quantity desc\n" +
            "limit 0,3; \n")
    List<PotentialCustomerDto> getAll();
}
