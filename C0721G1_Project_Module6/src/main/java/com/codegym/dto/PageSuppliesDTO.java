package com.codegym.dto;

import org.springframework.data.domain.Page;

public class PageSuppliesDTO {
    private String name;
    private String code;
    private String suppliesTypeId;
    private int page;
    private int size;

    public PageSuppliesDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuppliesTypeId() {
        return suppliesTypeId;
    }

    public void setSuppliesTypeId(String suppliesTypeId) {
        this.suppliesTypeId = suppliesTypeId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
