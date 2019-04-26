/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.CartItem;
import com.jets.ecommerce.dal.entity.CartItemId;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.User;
import java.util.List;


public interface CartItemsDao extends GenericDao<CartItem, CartItemId> {

    List<CartItem> findByUser(User user);

    void removeCartItem(User user, ProductItem productItem);

    void removeAllForUser(User user);

}
