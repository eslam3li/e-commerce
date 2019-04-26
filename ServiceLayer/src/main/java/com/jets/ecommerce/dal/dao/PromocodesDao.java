/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import java.util.List;

import com.jets.ecommerce.dal.entity.Promocode;


public interface PromocodesDao extends GenericDao<Promocode, Integer> {

    Promocode findByCode(String code);

    List<Promocode> findAll(Page page);

}
