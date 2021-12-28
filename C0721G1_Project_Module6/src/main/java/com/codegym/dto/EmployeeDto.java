package com.codegym.dto;

import com.codegym.dto.customValidate.BirthDay;
import com.codegym.model.Employee;
import com.codegym.model.Position;
import com.codegym.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


//duc
public class EmployeeDto implements Validator {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;


    @NotBlank
    @Size(max = 30, min = 2)
    @Pattern(regexp = "(\\p{L}+[\\p{L}\\s]*)", message = "Tên có chứa kí tự số hoặc kí tự đặc biệt")
    private String name;


    @NotBlank
    @Pattern(regexp = "^[\\d]{4}[-][\\d]{2}[-][\\d]{2}$", message = "nhập đúng định dang ngày là dd-mm-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @BirthDay(message = "Tuổi phải lớn hơn 18 và bé hơn 60")
    private String birthday;

    private String image;

    private Integer gender;

    @NotBlank
    @Pattern(regexp = "^((090)|(091))[\\d]{7}$",
            message = "Số điện thoại phải bắt đầu bằng 090xxxxxxx or 091xxxxxxx")
    private String phone;


    @NotBlank
    private String address;

    private Position position;

    private User user;


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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


    @Override
    public void validate(Object target, Errors errors) {

        EmployeeDto employeeDto = (EmployeeDto) target;
        if (employeeDto.getId() != null && employeeDto.getPhone() != null) {
            for (Employee employee : employeeList) {
                if (!employeeDto.getId().equals(employee.getId())) {
                    if (employeeDto.getPhone().equals(employee.getPhone())) {
                        errors.rejectValue("phone", "phone.equals", "Số điện thoại đã tồn tại");
                    }
                }
            }
        } else if (employeeDto.getPhone() != null) {
            for (Employee employee : employeeList) {
                if (employeeDto.getPhone().equals(employee.getPhone())) {
                    errors.rejectValue("phone", "phone.equals", "Số điện thoại đã tồn tại");
                }
            }
        }
    }
}
