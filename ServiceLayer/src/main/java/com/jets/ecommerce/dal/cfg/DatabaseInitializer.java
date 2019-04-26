package com.jets.ecommerce.dal.cfg;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DatabaseInitializer {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration().configure()
                .setProperty("hibernate.hbm2ddl.auto", "create");
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            LoremData loremData = new LoremData(sessionFactory);
            loremData.insertAll();
            DummyData.init(sessionFactory)
                    .insertAdmins()
                    .insertUsers()
                    .insertOrders();
        }
    }

}
