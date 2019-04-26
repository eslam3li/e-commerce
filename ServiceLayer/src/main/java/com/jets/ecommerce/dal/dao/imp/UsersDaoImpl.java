package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.*;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.beans.filters.UsersFilter;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class UsersDaoImpl extends GenericDaoImpl<User, Integer> implements UsersDao {

    public UsersDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User findByEmail(String email) {
        return (User) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public User findByPhone(String phone) {
        return (User) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("phone", phone))
                .uniqueResult();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return (User) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public List<User> findByFilter(UsersFilter filter, Page page) {
        Criteria criteria = currentSession().createCriteria(daoType);
        if (filter != null) {
            if (filter.getEmail() != null) {
                criteria.add(Restrictions.like("email", filter.getEmail().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getNameFilter() != null) {
                criteria.add(Restrictions.like("name", filter.getNameFilter().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getPhone() != null) {
                criteria.add(Restrictions.like("phone", filter.getPhone().getPattern(), MatchMode.ANYWHERE));
            }
        }
        if (page != null) {
            criteria.setFirstResult(page.getCurrentStart());
            criteria.setMaxResults(page.getPageSize());
        }
        return (List<User>) criteria.list();
    }

    @Override
    public int getUsersCount(UsersFilter filter) {
        Criteria criteria = currentSession().createCriteria(User.class);
        if (filter != null) {
            if (filter.getEmail() != null) {
                criteria.add(Restrictions.like("email", filter.getEmail().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getNameFilter() != null) {
                criteria.add(Restrictions.like("name", filter.getNameFilter().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getPhone() != null) {
                criteria.add(Restrictions.like("phone", filter.getPhone().getPattern(), MatchMode.ANYWHERE));
            }
        }
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }
}
