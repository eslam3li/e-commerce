
package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.CartItem;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.CartItemBean;


public class CartItemAdapter {

    public static CartItem fromBean(CartItemBean bean, User user, ProductItem productItem) {
        if (bean == null) {
            return null;
        }
        CartItem cartItem = new CartItem(user, productItem, bean.getQuantity());
        return cartItem;
    }

    public static CartItemBean toBean(CartItem entity) {
        if (entity == null) {
            return null;
        }
        CartItemBean cartItemBean
                = new CartItemBean(ProductItemAdapter.toBean(entity.getItem()),
                        entity.getQuantity());
        return cartItemBean;
    }

}
