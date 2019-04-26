/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.AdminsDao;
import com.jets.ecommerce.dal.dao.CartItemsDao;
import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.OrderItemsDao;
import com.jets.ecommerce.dal.dao.OrdersDao;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.ProductRatesDao;
import com.jets.ecommerce.dal.dao.ProductsDao;
import com.jets.ecommerce.dal.dao.PromocodesDao;
import com.jets.ecommerce.dal.dao.RechargeCardsDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import org.hibernate.SessionFactory;


public class DaosFactoryImpl implements DaosFactory {

    private final SessionFactory sessionFactory;

    public DaosFactoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AdminsDao getAdminsDao() {
        return new AdminsDaoImpl(sessionFactory);
    }

    @Override
    public CartItemsDao getCartItemsDao() {
        return new CartItemsDaoImpl(sessionFactory);
    }

    @Override
    public CategoriesDao getCategoriesDao() {
        return new CategoriesDaoImpl(sessionFactory);
    }

    @Override
    public OrderItemsDao getOrderItemsDao() {
        return new OrderItemsDaoImpl(sessionFactory);
    }

    @Override
    public OrdersDao getOrdersDao() {
        return new OrdersDaoImpl(sessionFactory);
    }

    @Override
    public ProductItemsDao getProductItemsDao() {
        return new ProductItemsDaoImpl(sessionFactory);
    }

    @Override
    public ProductRatesDao getProductRatesDao() {
        return new ProductRatesDaoImpl(sessionFactory);
    }

    @Override
    public ProductsDao getProductsDao() {
        return new ProductsDaoImpl(sessionFactory);
    }

    @Override
    public PromocodesDao getPromocodesDao() {
        return new PromocodesDaoImpl(sessionFactory);
    }

    @Override
    public RechargeCardsDao getRechargeCardsDao() {
        return new RechargeCardsDaoImpl(sessionFactory);
    }

    @Override
    public UsersDao getUsersDao() {
        return new UsersDaoImpl(sessionFactory);
    }

}
