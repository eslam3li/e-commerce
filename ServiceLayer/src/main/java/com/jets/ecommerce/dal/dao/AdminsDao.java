/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.Admin;


public interface AdminsDao extends GenericDao<Admin, Integer> {

    Admin findByEmail(String email);

    Admin findByEmailAndPassword(String email, String password);

}
