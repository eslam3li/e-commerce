/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.exceptions.RegisterationException;
import com.jets.ecommerce.service.exceptions.UnauthorizedActionExeption;
import com.jets.ecommerce.service.impl.ServicesProviderImpl;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthServiceTests extends EcommerceDBTestContext {

    AuthService authService;

    @BeforeAll
    void init() {
        SessionFactory sessionFactory = getSessionFactory();
        ServicesProvider servicesProvider = new ServicesProviderImpl(sessionFactory);
        authService = servicesProvider.getAuthService();
    }

    @AfterAll
    void close() {
        closeSessionFactory();
    }

    @Test
    @Order(10)
    void test_add_user_1() throws RegisterationException {
        UserBean userBean = new UserBean();
        userBean.setName("Ahmed");
        userBean.setPassword("ahmed");
        userBean.setBirthDate(new Date());
        userBean.setAddress("Egypt");
        userBean.setEmail("ahmed@gmail.com");
        authService.addUser(userBean);
    }

    @Test
    @Order(20)
    void test_add_user_2() {
        UserBean userBean = new UserBean();
        userBean.setName("Ahmed");
        userBean.setPassword("ahmed");
        userBean.setBirthDate(new Date());
        userBean.setAddress("Egypt");
        userBean.setEmail("ahmed@gmail.com");
        assertThrows(RegisterationException.class, () -> {
            authService.addUser(userBean);
        });
    }

    @Test
    @Order(30)
    void test_add_admin() {
        AdminBean adminBean = new AdminBean("admin", "admin2@admin.com", "admin");
        assertThrows(UnauthorizedActionExeption.class, () -> {
            authService.addAdmin(adminBean);
        });
    }

}
