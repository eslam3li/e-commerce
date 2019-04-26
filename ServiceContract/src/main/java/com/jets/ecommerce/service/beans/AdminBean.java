package com.jets.ecommerce.service.beans;


public class AdminBean {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public AdminBean() {
    }

    public AdminBean(Integer id) {
        this.id = id;
    }

    public AdminBean(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public AdminBean(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
