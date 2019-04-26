/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.RechargeCardBean;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.User;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;


public interface RechargeCardsService {

    /**
     * Used by the administrator to print or send throw email to users who want
     * to recharge balance
     *
     * @return
     */
    @Access(Admin.class)
    List<RechargeCardBean> getAllUnusedCards();

    /**
     * Use to make multiple recharge cards for a specific amount
     *
     * @param number
     * @param amount
     */
    @Access(Admin.class)
    void addRechargeCards(int number, int amount);

    /**
     * Used by the user to increase his balance
     *
     * @param code recharge card code issued by administrator
     */
    @Access(Client.class)
    void useRechargeCard(String code);

}
