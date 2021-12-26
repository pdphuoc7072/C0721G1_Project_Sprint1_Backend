package com.example.statsticals.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "order_date")
    private java.sql.Date orderDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "supplies_id")
    private Long suppliesId;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Date orderDate, Integer quantity, Long customerId, Long suppliesId) {
        this.id = id;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.customerId = customerId;
        this.suppliesId = suppliesId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(java.sql.Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSuppliesId() {
        return this.suppliesId;
    }

    public void setSuppliesId(Long suppliesId) {
        this.suppliesId = suppliesId;
    }
}
