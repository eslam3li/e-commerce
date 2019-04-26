/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import java.util.List;

import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.filters.UsersFilter;


public interface UsersDao extends GenericDao<User, Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);

    User findByEmailAndPassword(String email, String password);

    List<User> findByFilter(UsersFilter filter, Page page);

    int getUsersCount(UsersFilter filter);
}
