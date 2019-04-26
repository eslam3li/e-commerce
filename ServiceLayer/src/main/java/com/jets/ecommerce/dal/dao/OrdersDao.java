/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import java.util.List;

import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.filters.OrdersFilter;


public interface OrdersDao extends GenericDao<Order, Integer> {

    List<Order> findByUser(User user, Page page);

    List<Order> findByFilter(OrdersFilter filter, Page page);

    int getOrdersCount(OrdersFilter filter);

}
