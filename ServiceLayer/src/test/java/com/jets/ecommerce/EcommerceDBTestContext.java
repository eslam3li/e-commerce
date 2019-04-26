/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class EcommerceDBTestContext {

    private static final String TEST_DB_URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";

    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure()
                    .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                    .setProperty("hibernate.hbm2ddl.auto", "create")
                    .setProperty("hibernate.connection.url", TEST_DB_URL)
                    .setProperty("show_sql", "false")
                    .setProperty("format_sql", "false")
                    .setProperty("use_sql_comments", "false");
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    protected void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }
}
