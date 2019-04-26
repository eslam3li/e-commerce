/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.CartItemsDao;
import com.jets.ecommerce.dal.entity.CartItem;
import com.jets.ecommerce.dal.entity.CartItemId;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class CartItemsDaoImpl extends GenericDaoImpl<CartItem, CartItemId> implements CartItemsDao {

    public CartItemsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<CartItem> findByUser(User user) {
        return currentSession().createCriteria(daoType)
                .add(Restrictions.eq("user", user))
                .list();
    }

    @Override
    public void removeCartItem(User user, ProductItem productItem) {
        CartItem item = get(new CartItemId(user.getId(), productItem.getId()));
        delete(item);
    }

    @Override
    public void removeAllForUser(User user) {
        user.getCartItems().clear();
    }

}
