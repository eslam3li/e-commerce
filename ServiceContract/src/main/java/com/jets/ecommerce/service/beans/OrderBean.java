package com.jets.ecommerce.service.beans;

import java.util.Date;


public class OrderBean {

    private Integer id;
    private Date purchaseDate;
    private OrderState orderState;
    private UserBean user;

    public OrderBean() {
    }

    public OrderBean(Integer id) {
        this.id = id;
    }

    public OrderBean(Date purchaseDate, OrderState orderState, UserBean user) {
        this.purchaseDate = purchaseDate;
        this.orderState = orderState;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
