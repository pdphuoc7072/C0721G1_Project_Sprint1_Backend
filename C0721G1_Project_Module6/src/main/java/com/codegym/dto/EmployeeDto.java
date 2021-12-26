package com.codegym.dto;

import com.codegym.dto.customValidate.BirthDay;
import com.codegym.model.Position;
import com.codegym.model.User;
import javax.validation.constraints.*;



//duc
public class EmployeeDto {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @NotEmpty
    @NotBlank
    @Size(max = 60, min=3)
    @Pattern(regexp = "(\\p{L}+[\\p{L}\\s]*)", message = "Tên có chứa kí tự số")
    private String name;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^([\\d]{4})[-]([\\d]{2})[-]([\\d]{2})$", message = "nhập đúng định dang ngày là dd-mm-yyyy")
    @BirthDay(message = "Tuổi phải lớn hơn 18")
    private String birthday;

    private String image;

    private Integer gender;
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^((090)|(091))[\\d]{7}$",
            message = "Số điện thoại phải bắt đầu bằng 090xxxxxxx or 091xxxxxxx")
    private String phone;
    @NotEmpty
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

}
