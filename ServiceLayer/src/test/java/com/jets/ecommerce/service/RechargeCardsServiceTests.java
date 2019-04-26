/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.dal.cfg.DummyData;
import com.jets.ecommerce.service.impl.ServicesProviderImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class RechargeCardsServiceTests extends EcommerceDBTestContext {

    AuthService authService;

    @BeforeEach
    void init() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        ServicesProvider servicesProvider = new ServicesProviderImpl(sessionFactory);
        authService = servicesProvider.getAuthService();
        DummyData.init(getSessionFactory()).insertUsers();
        authService.loginUser("ib.yousre@gmail.com", "hima");
    }

    @AfterEach
    void close() {
        authService.logout();
        closeSessionFactory();
    }

}
