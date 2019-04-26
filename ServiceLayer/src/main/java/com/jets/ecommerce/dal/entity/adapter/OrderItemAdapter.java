
package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.OrderItem;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.service.beans.OrderItemBean;


public class OrderItemAdapter {

    public static OrderItem fromBean(OrderItemBean bean, Order order, ProductItem productItem) {
        if (bean == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem(order, productItem);
        orderItem.setQuantity(bean.getQuantity());
        return orderItem;
    }

    public static OrderItemBean toBean(OrderItem entity) {
        if (entity == null) {
            return null;
        }
        OrderItemBean orderItemBean = new OrderItemBean();
        orderItemBean.setQuantity(entity.getQuantity());
        orderItemBean.setProductItem(ProductItemAdapter.toBean(entity.getItem()));
        return orderItemBean;
    }

}
