package com.codegym.model;



import javax.persistence.*;

@Entity
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private Long price;

    private String productionDate;

    private String expiryDate;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column(columnDefinition = "TEXT")
    private String technicalInformation;

    private String image;

    @ManyToOne(targetEntity = SuppliesType.class)
    @JoinColumn(name = "supplies_type_id", referencedColumnName = "id")
    private SuppliesType suppliesType;

    @ManyToOne(targetEntity = Producer.class)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @ManyToOne()
    @JoinColumn(name = "orderDetail_id", referencedColumnName = "id")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    public Supplies() {
    }

    public Supplies(Long id, String code, String name, Long price, String productionDate, String expiryDate, String introduce, String technicalInformation, String image, SuppliesType suppliesType, Producer producer) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.introduce = introduce;
        this.technicalInformation = technicalInformation;
        this.image = image;
        this.suppliesType = suppliesType;
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTechnicalInformation() {
        return technicalInformation;
    }

    public void setTechnicalInformation(String technicalInformation) {
        this.technicalInformation = technicalInformation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SuppliesType getSuppliesType() {
        return suppliesType;
    }

    public void setSuppliesType(SuppliesType suppliesType) {
        this.suppliesType = suppliesType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
