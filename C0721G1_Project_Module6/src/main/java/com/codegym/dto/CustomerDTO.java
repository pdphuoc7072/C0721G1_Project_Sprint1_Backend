package com.codegym.dto;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.model.OrderDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;


// Tân Code  24/12/2021//
public class CustomerDTO  implements Validator {
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    private Address address;
    private Iterable<Customer> customers;
    public CustomerDTO() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Iterable<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Iterable<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
