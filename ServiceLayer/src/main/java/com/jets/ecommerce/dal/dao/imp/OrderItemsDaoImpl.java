/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.OrderItemsDao;
import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.OrderItem;
import com.jets.ecommerce.dal.entity.OrderItemId;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class OrderItemsDaoImpl extends GenericDaoImpl<OrderItem, OrderItemId> implements OrderItemsDao {

    public OrderItemsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return currentSession().createCriteria(daoType)
                .add(Restrictions.eq("order", order))
                .list();
    }

}
