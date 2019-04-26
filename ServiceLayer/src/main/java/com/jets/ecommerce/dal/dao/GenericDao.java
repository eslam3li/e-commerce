/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import java.util.List;


public interface GenericDao<E, K> {

    public void save(E entity);

    public void saveOrUpdate(E entity);

    public void update(E entity);

    public void delete(E entity);

    public E get(K key);

    public E load(K key);

    public List<E> findAll();
}
