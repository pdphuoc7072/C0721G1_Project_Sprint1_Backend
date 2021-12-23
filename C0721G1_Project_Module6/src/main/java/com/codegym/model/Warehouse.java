package com.codegym.model;

import javax.persistence.*;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer brokenSupplies;

    private Integer normalSupplies;

    private String importDate;

    private Integer price;

    private Integer quantity;

    private String unit;

    @ManyToOne(targetEntity = Supplies.class)
    @JoinColumn(name = "supplies_id", referencedColumnName = "id")
    private Supplies supplies;

    public Warehouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrokenSupplies() {
        return brokenSupplies;
    }

    public void setBrokenSupplies(Integer brokenSupplies) {
        this.brokenSupplies = brokenSupplies;
    }

    public Integer getNormalSupplies() {
        return normalSupplies;
    }

    public void setNormalSupplies(Integer normalSupplies) {
        this.normalSupplies = normalSupplies;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Supplies getSupplies() {
        return supplies;
    }

    public void setSupplies(Supplies supplies) {
        this.supplies = supplies;
    }
}