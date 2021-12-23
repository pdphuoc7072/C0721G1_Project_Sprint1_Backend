package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SuppliesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "suppliesType")
    private Set<Supplies> supplies;

    public SuppliesType() {
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

    public Set<Supplies> getSupplies() {
        return supplies;
    }

    public void setSupplies(Set<Supplies> supplies) {
        this.supplies = supplies;
    }
}
