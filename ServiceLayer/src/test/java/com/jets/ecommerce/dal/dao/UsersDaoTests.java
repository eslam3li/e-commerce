/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.User;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersDaoTests extends GenericDaoTests {

    private static final String IBRAHIM_EMAIL = "ib.yousre@gmail.com";

    @BeforeAll
    @Override
    void init() {
        super.init();
    }

    @AfterAll
    @Override
    protected void closeSessionFactory() {
        super.closeSessionFactory();
    }

    @Test
    @Order(10)
    void test_load_all_when_empty() throws Exception {
        transactionManager.runInTransaction(() -> {
            List<User> allUsers = usersDao.findAll();
            assertNotNull(allUsers);
            assertEquals(allUsers.size(), 0);
            return allUsers;
        });
    }

    @Test
    @Order(20)
    void test_insert_users() throws Exception {
        User userInserted = new User("Ibrahim", IBRAHIM_EMAIL, "hima");
        transactionManager.runInTransaction(() -> {
            usersDao.save(userInserted);
            return null;
        });
        assertNotNull(userInserted.getId());
        transactionManager.runInTransaction(() -> {
            User userLoaded = usersDao.findByEmail(IBRAHIM_EMAIL);
            assertNotNull(userLoaded);
            assertEquals(userInserted.getId(), userLoaded.getId());
            assertEquals(userInserted.getName(), userLoaded.getName());
            assertEquals(userInserted.getEmail(), userLoaded.getEmail());
            return null;
        });
    }

    @Test
    @Order(30)
    void test_insert_existing_user() {
        User userInserted = new User("Ibrahim", IBRAHIM_EMAIL, "hima");
        assertThrows(Exception.class, () -> {
            transactionManager.runInTransaction(() -> {
                usersDao.save(userInserted);
                return null;
            });
        });
    }

    @Test
    @Order(40)
    void test_insert_another_user() throws Exception {
        final String AHMED_EMAIL = "ahmed@gmail.com";
        User userInserted = new User("Ahmed", AHMED_EMAIL, "ahmed");
        transactionManager.runInTransaction(() -> {
            usersDao.save(userInserted);
            return null;
        });
        assertNotNull(userInserted.getId());
        transactionManager.runInTransaction(() -> {
            User userLoaded = usersDao.findByEmail(AHMED_EMAIL);
            assertNotNull(userLoaded);
            assertEquals(userInserted.getId(), userLoaded.getId());
            assertEquals(userInserted.getName(), userLoaded.getName());
            assertEquals(userInserted.getEmail(), userLoaded.getEmail());
            return null;
        });
    }

    @Test
    @Order(50)
    void test_load_all() throws Exception {
        transactionManager.runInTransaction(() -> {
            List<User> allUsers = usersDao.findAll();
            assertNotNull(allUsers);
            assertEquals(allUsers.size(), 2);
            return allUsers;
        });
    }

}
