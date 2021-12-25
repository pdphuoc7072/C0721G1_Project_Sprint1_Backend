package com.codegym.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterRequest {
    private String code;

    @NotBlank(message = "Vui lòng nhập Tên đăng nhập")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){2,8}[a-zA-Z0-9]$",
            message = "Vui lòng nhập đúng định dạng của Tên đăng nhập")
    private String username;

    @NotBlank(message = "Vui lòng nhập Mật khẩu")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$",
            message = "Vui lòng nhập đúng định dạng của Mật khẩu")
    private String password;
    private String role;

    public RegisterRequest() {
    }

    public RegisterRequest(String code) {
        this.code = code;
    }

    public RegisterRequest(String code, String username, String password, String role) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
