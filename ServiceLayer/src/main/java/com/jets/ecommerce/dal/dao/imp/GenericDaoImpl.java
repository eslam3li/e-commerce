/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.Entity;
import com.jets.ecommerce.dal.dao.GenericDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<E extends Entity<K>, K extends Serializable>
        implements GenericDao<E, K> {

    private final SessionFactory sessionFactory;

    protected final Class<? extends E> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object.There would be
     * exception because Object class does not hava constructor with parameters.
     *
     * @param sessionFactory
     */
    public GenericDaoImpl(SessionFactory sessionFactory) {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E get(K key) {
        return currentSession().get(daoType, key);
    }

    @Override
    public E load(K key) {
        return currentSession().load(daoType, key);
    }

    @Override
    public List<E> findAll() {
        return currentSession().createCriteria(daoType).list();
    }
}
