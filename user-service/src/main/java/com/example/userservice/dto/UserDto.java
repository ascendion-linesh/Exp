package com.example.userservice.dto;

import java.math.BigDecimal;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer totalOrders;
    private BigDecimal totalSpent;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getTotalOrders() {
        return totalOrders;
    }
    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }
    public BigDecimal getTotalSpent() {
        return totalSpent;
    }
    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}