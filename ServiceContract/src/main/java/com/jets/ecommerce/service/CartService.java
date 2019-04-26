/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;
import java.util.Set;


public interface CartService {

    @Access(Client.class)
    List<CartItemBean> getMyCartItems();

    @Access(Client.class)
    void addCartItem(CartItemBean cartItemBean);
    
    @Access(Client.class)
    void addCartItems(Set<CartItemBean> cartItemBeans);

    @Access(Client.class)
    void removeProductItemFromCart(int productItemId);

    @Access(Client.class)
    void updateCartItem(CartItemBean cartItemBean);

    @Access(Client.class)
    void updateCart(List<CartItemBean> cartItemBeans);

    @Access(Client.class)
    void clearCart();
}
