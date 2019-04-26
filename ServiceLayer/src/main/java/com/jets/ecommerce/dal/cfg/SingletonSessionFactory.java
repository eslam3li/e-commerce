package com.jets.ecommerce.dal.cfg;

import java.net.URI;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public final class SingletonSessionFactory {

    private static final SessionFactory SESSION_FACTORY = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        try {
            boolean create = false;
            Configuration cfg = new Configuration().configure();
            if (System.getenv("DATABASE_URL") != null) {
                URI dbUri = new URI(System.getenv("DATABASE_URL"));
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort()
                        + dbUri.getPath();
                cfg = cfg.setProperty("hibernate.connection.url", dbUrl)
                        .setProperty("hibernate.connection.username", username)
                        .setProperty("hibernate.connection.password", password)
                        .setProperty("hibernate.connection.driver_class",
                                "org.postgresql.Driver")
                        .setProperty("hibernate.dialect",
                                "org.hibernate.dialect.PostgreSQLDialect");
                if (create) {
                    cfg = cfg.setProperty("hibernate.hbm2ddl.auto", "create");
                }
            }
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            if (create) {
                LoremData loremData = new LoremData(sessionFactory);
                loremData.insertAll();
                DummyData.init(sessionFactory)
                        .insertAdmins()
                        .insertUsers()
                        .insertOrders();
            }
            return sessionFactory;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to initialize database", ex);
        }
    }

    private SingletonSessionFactory() {
    }

    public static SessionFactory getInstance() {
        return SESSION_FACTORY;
    }

}
