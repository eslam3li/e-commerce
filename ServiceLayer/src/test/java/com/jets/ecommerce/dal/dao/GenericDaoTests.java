/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.ibrahim.hibernate.transaction.TransactionManager;
import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.dal.dao.imp.AdminsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.CartItemsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.CategoriesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.OrderItemsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.OrdersDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductItemsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductRatesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.PromocodesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.RechargeCardsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.UsersDaoImpl;


public class GenericDaoTests extends EcommerceDBTestContext {

    protected TransactionManager transactionManager;
    protected AdminsDao adminsDao;
    protected CartItemsDao cartItemsDao;
    protected CategoriesDao categoriesDao;
    protected OrdersDao ordersDao;
    protected OrderItemsDao orderItemsDao;
    protected ProductsDao productsDao;
    protected ProductItemsDao productItemsDao;
    protected ProductRatesDao productRatesDao;
    protected PromocodesDao promocodesDao;
    protected RechargeCardsDao rechargeCardsDao;
    protected UsersDao usersDao;

    void init() {
        transactionManager = new TransactionManager(getSessionFactory());

        adminsDao = new AdminsDaoImpl(getSessionFactory());
        cartItemsDao = new CartItemsDaoImpl(getSessionFactory());
        categoriesDao = new CategoriesDaoImpl(getSessionFactory());
        ordersDao = new OrdersDaoImpl(getSessionFactory());
        orderItemsDao = new OrderItemsDaoImpl(getSessionFactory());
        productsDao = new ProductsDaoImpl(getSessionFactory());
        productItemsDao = new ProductItemsDaoImpl(getSessionFactory());
        productRatesDao = new ProductRatesDaoImpl(getSessionFactory());
        promocodesDao = new PromocodesDaoImpl(getSessionFactory());
        rechargeCardsDao = new RechargeCardsDaoImpl(getSessionFactory());
        usersDao = new UsersDaoImpl(getSessionFactory());
    }

    void close() {
        closeSessionFactory();
    }

}
