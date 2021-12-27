package com.example.statsticals.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "broken_supplies")
    private Integer brokenSupplies;

    @Column(name = "normal_supplies")
    private Integer normalSupplies;

    @Column(name = "import_date")
    private java.sql.Date importDate;

    @Column(name = "import_quantity")
    private Integer importQuantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "supplies_id")
    private Long suppliesId;

    @Column(name = "unit")
    private String unit;

    @Column(name = "refund_supplies")
    private Integer refundSupplies;

    @Column(name = "cancelled_supplies")
    private Integer cancelledSupplies;

    public Warehouse() {
    }

    public Warehouse(Long id, String name, Integer brokenSupplies, Integer normalSupplies, Date importDate, Integer importQuantity, Integer price, Integer quantity, Long suppliesId, String unit, Integer refundSupplies, Integer cancelledSupplies) {
        this.id = id;
        this.name = name;
        this.brokenSupplies = brokenSupplies;
        this.normalSupplies = normalSupplies;
        this.importDate = importDate;
        this.importQuantity = importQuantity;
        this.price = price;
        this.quantity = quantity;
        this.suppliesId = suppliesId;
        this.unit = unit;
        this.refundSupplies = refundSupplies;
        this.cancelledSupplies = cancelledSupplies;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrokenSupplies() {
        return this.brokenSupplies;
    }

    public void setBrokenSupplies(Integer brokenSupplies) {
        this.brokenSupplies = brokenSupplies;
    }

    public Integer getNormalSupplies() {
        return this.normalSupplies;
    }

    public void setNormalSupplies(Integer normalSupplies) {
        this.normalSupplies = normalSupplies;
    }

    public java.sql.Date getImportDate() {
        return this.importDate;
    }

    public void setImportDate(java.sql.Date importDate) {
        this.importDate = importDate;
    }

    public Integer getImportQuantity() {
        return this.importQuantity;
    }

    public void setImportQuantity(Integer importQuantity) {
        this.importQuantity = importQuantity;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getSuppliesId() {
        return this.suppliesId;
    }

    public void setSuppliesId(Long suppliesId) {
        this.suppliesId = suppliesId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRefundSupplies() {
        return this.refundSupplies;
    }

    public void setRefundSupplies(Integer refundSupplies) {
        this.refundSupplies = refundSupplies;
    }

    public Integer getCancelledSupplies() {
        return this.cancelledSupplies;
    }

    public void setCancelledSupplies(Integer cancelledSupplies) {
        this.cancelledSupplies = cancelledSupplies;
    }
}
