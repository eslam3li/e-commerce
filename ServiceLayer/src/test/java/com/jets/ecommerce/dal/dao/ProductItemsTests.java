/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.cfg.DummyData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ProductItemsTests extends GenericDaoTests {

    @BeforeEach
    @Override
    void init() {
        super.init();
    }

    @AfterEach
    @Override
    void close() {
        super.close();
    }

    @Test
    void test_find_all() throws Exception {
        transactionManager.runInTransaction(() -> {
            Assertions.assertEquals(0, productItemsDao.findAll().size());
            return null;
        });
        DummyData.init(getSessionFactory()).insertProducts();
        transactionManager.runInTransaction(() -> {
            Assertions.assertEquals(40, productItemsDao.findAll().size());
            return null;
        });
    }

}
