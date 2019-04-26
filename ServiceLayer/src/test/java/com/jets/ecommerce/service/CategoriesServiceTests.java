/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.dal.cfg.DummyData;
import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.service.impl.ServicesProviderImpl;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CategoriesServiceTests extends EcommerceDBTestContext {

    CategoriesService categoriesService;
    AuthService authService;

    @BeforeEach
    void init() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        ServicesProvider servicesProvider = new ServicesProviderImpl(sessionFactory);
        categoriesService = servicesProvider.getCategoriesService();
        authService = servicesProvider.getAuthService();
        DummyData.init(getSessionFactory()).insertAll();
    }

    @AfterEach
    void close() {
        closeSessionFactory();
    }

    @Test
    void test_get_all_categories() {
        List<CategoryBean> allCategories = categoriesService.getAllCategories();
        assertEquals(5, allCategories.size());
    }

    @Test
    void test_get_top_level_categories() {
        List<CategoryBean> topLevelCategories = categoriesService.getTopLevelCategories();
        assertEquals(3, topLevelCategories.size());
    }

    @Test
    void test_get_sub_categories() {
        List<CategoryBean> allCategories = categoriesService.getAllCategories();
        for (CategoryBean categoryBean : allCategories) {
            List<CategoryBean> subCategories
                    = categoriesService.getSubCategories(categoryBean.getId());
            assertEquals(categoryBean.getName().equals("Men") ? 2 : 0, subCategories.size());
        }
    }

    @Test
    void test_get_category() {
        List<CategoryBean> allCategories = categoriesService.getAllCategories();
        for (CategoryBean category : allCategories) {
            List<CategoryBean> subCategories
                    = categoriesService.getSubCategories(category.getId());
            for (CategoryBean subCategory : subCategories) {
                CategoryBean cat = categoriesService.getCategory(subCategory.getId());
                assertEquals(subCategory.getName(), cat.getName());
                assertEquals(subCategory.getId(), cat.getId());
                assertEquals(subCategory.getPicture(), cat.getPicture());
                assertEquals(category.getName(), cat.getParentCategory().getName());
                assertEquals(category.getId(), cat.getParentCategory().getId());
                assertEquals(category.getPicture(), cat.getParentCategory().getPicture());
            }
        }
    }

    @Test
    void test_add_category() throws Exception {
        authService.loginAdmin("admin@admin.com", "admin");

        CategoryBean categoryBean = new CategoryBean("new", null);
        categoryBean.setPicture("picture");
        int parentId = categoriesService.getAllCategories().get(4).getId();
        categoriesService.addCategory(parentId, categoryBean);
        List<CategoryBean> allCategories = categoriesService.getAllCategories();
        assertEquals(6, allCategories.size());
        CategoryBean last = allCategories.get(allCategories.size() - 1);
        CategoryBean inserted = categoriesService.getCategory(last.getId());
        assertEquals(inserted.getParentCategory().getId(), parentId);
        assertEquals(inserted.getName(), "new");
        assertEquals(inserted.getPicture(), "picture");
        assertEquals(3, categoriesService.getTopLevelCategories().size());
    }

    @Test
    void test_add_category_top_level() throws Exception {
        authService.loginAdmin("admin@admin.com", "admin");

        CategoryBean categoryBean = new CategoryBean("new", null);
        categoryBean.setPicture("picture");
        categoriesService.addCategory(null, categoryBean);
        List<CategoryBean> allCategories = categoriesService.getAllCategories();
        assertEquals(6, allCategories.size());
        CategoryBean last = allCategories.get(allCategories.size() - 1);
        CategoryBean inserted = categoriesService.getCategory(last.getId());
        assertNull(inserted.getParentCategory());
        assertEquals(inserted.getName(), "new");
        assertEquals(inserted.getPicture(), "picture");
        assertEquals(4, categoriesService.getTopLevelCategories().size());
    }

    @Test
    void test_update_category() throws Exception {
        authService.loginAdmin("admin@admin.com", "admin");

        CategoryBean category = categoriesService.getAllCategories().get(0);
        category.setName("updated");
        categoriesService.updateCategory(category);
        CategoryBean updated = categoriesService.getCategory(category.getId());
        assertEquals(category.getName(), updated.getName());
        assertEquals(category.getPicture(), updated.getPicture());
    }

//    @Test
//    void test_remove_category() {
//        CategoryBean category = categoriesService.getAllCategories().get(4);
//        categoriesService.removeCategory(category.getId());
//        assertEquals(4, categoriesService.getAllCategories());
//    }
}
