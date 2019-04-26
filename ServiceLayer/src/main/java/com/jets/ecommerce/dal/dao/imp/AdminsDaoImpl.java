/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.entity.Admin;
import org.hibernate.SessionFactory;
import com.jets.ecommerce.dal.dao.AdminsDao;
import org.hibernate.criterion.Restrictions;


public class AdminsDaoImpl extends GenericDaoImpl<Admin, Integer> implements AdminsDao {

    public AdminsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Admin findByEmail(String email) {
        return (Admin) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        return (Admin) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

}
