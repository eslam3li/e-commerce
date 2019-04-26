/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.OrderItem;
import com.jets.ecommerce.dal.entity.OrderItemId;
import java.util.List;


public interface OrderItemsDao extends GenericDao<OrderItem, OrderItemId> {

    List<OrderItem> findByOrder(Order order);
}
