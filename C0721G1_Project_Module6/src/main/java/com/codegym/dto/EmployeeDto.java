package com.codegym.dto;

import com.codegym.model.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDto implements Validator {

    private Long id;

    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "^(NV-)(\\d{4})$", message = "Employee Code must be in the correct format: NV-XXXX")
    private String code;

    @Size(min = 5, max = 15)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Birthday cannot be blank")
    @Pattern(regexp = "^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$",
            message = "Birthday must be in the correct format: YYYY-MM-DD")
    private String dateOfBirth;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @NotNull(message = "Gender cannot be blank")
    private Integer gender;


    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^(0|(\\(84\\)\\+))+([9][0-1][0-9]{7})$",
            message = "Phone must be in the correct format: 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx")
    private String phone;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private Position position;

    private User user;

    List<Employee> employees = new ArrayList<>();

//    private boolean checkPhone;

    public EmployeeDto() {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }


}