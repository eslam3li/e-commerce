/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import java.util.List;

import com.jets.ecommerce.dal.entity.RechargeCard;


public interface RechargeCardsDao extends GenericDao<RechargeCard, Integer> {

    RechargeCard findByCode(String code);

    List<RechargeCard> findUnused();

}
