package com.jets.ecommerce.service.beans;


public class RechargeCardBean {

    private Integer id;
    private String code;
    private int ammount;

    public RechargeCardBean() {
    }

    public RechargeCardBean(Integer id) {
        this.id = id;
    }

    public RechargeCardBean(String code, int ammount) {
        this.code = code;
        this.ammount = ammount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

}
