
package com.jets.ecommerce.service.impl;

import com.ibrahim.hibernate.transaction.TransactionManager;
import com.jets.ecommerce.dal.cfg.SingletonSessionFactory;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.imp.DaosFactoryImpl;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.CartService;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.OrdersService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.PromocodesService;
import com.jets.ecommerce.service.RechargeCardsService;
import com.jets.ecommerce.service.ServicesProvider;
import com.jets.ecommerce.service.proxies.AccessProxy;
import com.jets.ecommerce.service.proxies.TransactionProxy;
import org.hibernate.SessionFactory;


public class ServicesProviderImpl implements ServicesProvider {

    SessionFactory sessionFactory;
    DaosFactory daosFactory;
    TransactionManager transactionManager;

    public ServicesProviderImpl() {
        sessionFactory = SingletonSessionFactory.getInstance();
        daosFactory = new DaosFactoryImpl(sessionFactory);
        transactionManager = new TransactionManager(sessionFactory);
    }

    public ServicesProviderImpl(SessionFactory sessionFactory) {
        daosFactory = new DaosFactoryImpl(sessionFactory);
        transactionManager = new TransactionManager(sessionFactory);
    }

    private <T> T wrap(T service) {
        return AccessProxy.wrap(TransactionProxy.wrap(service, transactionManager));
    }

    @Override
    public AuthService getAuthService() {
        return wrap(new AuthServiceImpl(daosFactory));
    }

    @Override
    public CartService getCartService() {
        return wrap(new CartServiceImpl(daosFactory));
    }

    @Override
    public CategoriesService getCategoriesService() {
        return wrap(new CategoriesServiceImpl(daosFactory));
    }

    @Override
    public OrdersService getOrdersService() {
        return wrap(new OrdersServiceImpl(daosFactory));
    }

    @Override
    public ProductsService getProductsService() {
        return wrap(new ProductsServiceImpl(daosFactory));
    }

    @Override
    public PromocodesService getPromocodesService() {
        return wrap(new PromocodesServiceImpl(daosFactory));
    }

    @Override
    public RechargeCardsService getRechargeCardsService() {
        return wrap(new RechargeCardsServiceImpl(daosFactory));
    }

    @Override
    public void goodBye() {
        sessionFactory.close();
    }

}
