/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;


public interface DaosFactory {

    AdminsDao getAdminsDao();

    CartItemsDao getCartItemsDao();

    CategoriesDao getCategoriesDao();

    OrderItemsDao getOrderItemsDao();

    OrdersDao getOrdersDao();

    ProductItemsDao getProductItemsDao();

    ProductRatesDao getProductRatesDao();

    ProductsDao getProductsDao();

    PromocodesDao getPromocodesDao();

    RechargeCardsDao getRechargeCardsDao();

    UsersDao getUsersDao();
}
