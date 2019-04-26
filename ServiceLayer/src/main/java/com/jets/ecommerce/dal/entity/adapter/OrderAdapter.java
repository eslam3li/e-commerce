
package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.OrderBean;


public class OrderAdapter {

    public static Order fromBean(OrderBean bean, User user) {
        if (bean == null) {
            return null;
        }
        Order order = new Order(bean.getPurchaseDate(), bean.getOrderState(), user);
        return order;
    }

    public static OrderBean toBean(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderBean orderBean = new OrderBean(entity.getId());
        orderBean.setOrderState(entity.getState());
        orderBean.setPurchaseDate(entity.getPurchaseDate());
        orderBean.setUser(UserAdapter.toBean(entity.getUser()));
        return orderBean;
    }

}
