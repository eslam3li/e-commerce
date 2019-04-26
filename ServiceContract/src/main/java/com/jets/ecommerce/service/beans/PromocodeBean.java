package com.jets.ecommerce.service.beans;

import java.math.BigDecimal;


public class PromocodeBean {

    private Integer id;
    private String code;
    private BigDecimal discount;

    public PromocodeBean() {
    }

    public PromocodeBean(Integer id) {
        this.id = id;
    }

    public PromocodeBean(String code, BigDecimal discount) {
        this.code = code;
        this.discount = discount;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

}
