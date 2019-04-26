/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.beans.filters;

import com.jets.ecommerce.service.beans.OrderState;


public class OrdersFilter {

    PatternFilter usernameFilter;
    PatternFilter emailFilter;
    OrderState orderState;

    public OrdersFilter() {
    }

    public OrdersFilter(PatternFilter usernameFilter, PatternFilter emailFilter, OrderState orderState) {
        this.usernameFilter = usernameFilter;
        this.emailFilter = emailFilter;
        this.orderState = orderState;
    }

    public PatternFilter getUsernameFilter() {
        return usernameFilter;
    }

    public void setUsernameFilter(PatternFilter usernameFilter) {
        this.usernameFilter = usernameFilter;
    }

    public PatternFilter getEmailFilter() {
        return emailFilter;
    }

    public void setEmailFilter(PatternFilter emailFilter) {
        this.emailFilter = emailFilter;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

}
