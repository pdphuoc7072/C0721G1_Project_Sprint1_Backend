package com.example.statsticals.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "supplies")
public class Supplies {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "supplies_type_id")
    private Long suppliesTypeId;

    @Column(name = "price")
    private Long price;

    @Column(name = "production_date")
    private java.sql.Date productionDate;

    @Column(name = "expiry_date")
    private java.sql.Date expiryDate;

    @Column(name = "producer_id")
    private Long producerId;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "technical_infomation")
    private String technicalInfomation;

    @Column(name = "image")
    private String image;

    public Supplies() {
    }

    public Supplies(Long id, String code, String name, Long suppliesTypeId, Long price, Date productionDate, Date expiryDate, Long producerId, String introduce, String technicalInfomation, String image) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.suppliesTypeId = suppliesTypeId;
        this.price = price;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.producerId = producerId;
        this.introduce = introduce;
        this.technicalInfomation = technicalInfomation;
        this.image = image;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSuppliesTypeId() {
        return this.suppliesTypeId;
    }

    public void setSuppliesTypeId(Long suppliesTypeId) {
        this.suppliesTypeId = suppliesTypeId;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public java.sql.Date getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(java.sql.Date productionDate) {
        this.productionDate = productionDate;
    }

    public java.sql.Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(java.sql.Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getProducerId() {
        return this.producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTechnicalInfomation() {
        return this.technicalInfomation;
    }

    public void setTechnicalInfomation(String technicalInfomation) {
        this.technicalInfomation = technicalInfomation;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
