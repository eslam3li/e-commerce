/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import java.util.Iterator;
import java.util.ServiceLoader;


public interface ServicesProvider {

    AuthService getAuthService();

    CartService getCartService();

    CategoriesService getCategoriesService();

    OrdersService getOrdersService();

    ProductsService getProductsService();

    PromocodesService getPromocodesService();

    RechargeCardsService getRechargeCardsService();

    void goodBye();

    /**
     * Make sure you only call this once throw the application and not in every
     * request. You can get instance in application start and save it in context
     *
     * @return
     */
    static ServicesProvider getInstance() {
        ServiceLoader<ServicesProvider> serviceLoader = ServiceLoader.load(ServicesProvider.class);
        Iterator<ServicesProvider> iterator = serviceLoader.iterator();
        if (!iterator.hasNext()) {
            throw new RuntimeException("No services provider found");
        }
        return iterator.next();
    }
}
