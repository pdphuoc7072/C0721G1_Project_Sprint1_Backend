package com.example.statsticals.model;

import javax.persistence.*;

@Entity
@Table(name = "use_role")
public class UseRole {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

    public UseRole() {
    }

    public UseRole(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
