/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.OrdersDao;
import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.filters.OrdersFilter;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class OrdersDaoImpl extends GenericDaoImpl<Order, Integer> implements OrdersDao {

    public OrdersDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Order> findByUser(User user, Page page) {
        Criteria criteria = currentSession().createCriteria(daoType)
                .add(Restrictions.eq("user", user));
        if (page != null) {
        	if(page.getCurrentStart() > 0) {
        		criteria.setFirstResult(page.getCurrentStart());
        	}
        	if(page.getPageSize() > 0) {
        		criteria.setMaxResults(page.getPageSize());
        	}
        }
        return (List<Order>) criteria.list();
    }

    @Override
    public List<Order> findByFilter(OrdersFilter filter, Page page) {
        Criteria criteria = getCriteria(filter);
        if (page != null) {
            criteria.setFirstResult(page.getCurrentStart());
            criteria.setMaxResults(page.getPageSize());
        }
        return criteria.list();
    }

    @Override
    public int getOrdersCount(OrdersFilter filter) {
        Criteria criteria = getCriteria(filter);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    private Criteria getCriteria(OrdersFilter filter) {
        Criteria criteria = currentSession().createCriteria(daoType);
        if (filter != null) {
            Criteria userCriteria = criteria.createCriteria("user");
            if (filter.getUsernameFilter() != null) {
                userCriteria.add(Restrictions.like("name", filter.getUsernameFilter().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getEmailFilter() != null) {
                userCriteria.add(Restrictions.like("email", filter.getEmailFilter().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getOrderState() != null) {
                criteria.add(Restrictions.eq("state", filter.getOrderState()));
            }
        }
        return criteria;
    }
}
