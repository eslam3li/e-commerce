/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.dal.cfg.DummyData;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.service.impl.ServicesProviderImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ProductsServiceTests extends EcommerceDBTestContext {

    ProductsService productsService;
    CategoriesService categoriesService;
    AuthService authService;

    @BeforeEach
    void init() {
        SessionFactory sessionFactory = getSessionFactory();
        ServicesProvider servicesProvider = new ServicesProviderImpl(sessionFactory);
        productsService = servicesProvider.getProductsService();
        categoriesService = servicesProvider.getCategoriesService();
        authService = servicesProvider.getAuthService();
    }

    @AfterEach
    void close() {
        closeSessionFactory();
    }

    @Test
    void test_search_1() {
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        assertEquals(0, productBeans.size());
    }

    @Test
    void test_search_2() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        assertEquals(20, productBeans.size());
    }

    @Test
    void test_get_product() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        ProductBean actual = productsService.getProduct(productBeans.get(0).getId());
        assertEquals(productBeans.get(0).getName(), actual.getName());
    }

    @Test
    void test_get_product_items() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        assertEquals(2, productItemBeans.size());
    }

    @Test
    void test_add_product() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts().insertAdmins();
        authService.loginAdmin("admin@admin.com", "admin");

        List<String> pictures = Arrays.asList(new String[]{"hi"});
        Set<ProductItemBean> productItemBeans = new HashSet<>();
        productItemBeans.addAll(Arrays.asList(new ProductItemBean[]{
            new ProductItemBean("", 10, null)
        }));
        ProductBean productBean = new ProductBean("pname", "description",
                "defaultPicture", BigDecimal.TEN, BigDecimal.TEN, null);
        productBean.getPictures().addAll(pictures);
        productBean.setSale(BigDecimal.ZERO);

        int categoryId = categoriesService.getAllCategories().get(0).getId();
        productsService.addProduct(categoryId, productBean, productItemBeans);

        List<ProductBean> productBeans
                = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        assertEquals(21, productBeans.size());
    }

    @Test
    void test_remove_product() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts().insertAdmins();
        authService.loginAdmin("admin@admin.com", "admin");

        List<ProductBean> productBeans = productsService.searchProducts(null, 0, Integer.MAX_VALUE);
        assertEquals(20, productBeans.size());
        productsService.removeProduct(productBeans.get(0).getId());
        assertEquals(19, productsService.searchProducts(null, 0, Integer.MAX_VALUE).size());
    }

    @Test
    void test_update_product() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts().insertAdmins();
        authService.loginAdmin("admin@admin.com", "admin");

        int productId = productsService.searchProducts(null, 0, Integer.MAX_VALUE).get(0).getId();

        List<String> pictures = Arrays.asList(new String[]{"hi"});
        Set<ProductItemBean> productItemBeans = new HashSet<>();
        productItemBeans.addAll(Arrays.asList(new ProductItemBean[]{
            new ProductItemBean("", 10, null)
        }));
        ProductBean productBean = new ProductBean("pname", "description",
                "defaultPicture", BigDecimal.TEN, BigDecimal.TEN, null);
        productBean.getPictures().addAll(pictures);
        productBean.setSale(BigDecimal.ZERO);
        productBean.setId(productId);
        productsService.updateProduct(productBean, productItemBeans);

        assertEquals(20, productsService.searchProducts(null, 0, Integer.MAX_VALUE).size());

        productBean = productsService.getProduct(productId);
        List<ProductItemBean> productItems = productsService.getProductItems(productId);
        assertEquals("pname", productBean.getName());
        assertEquals("description", productBean.getDescription());
        assertEquals("defaultPicture", productBean.getDisplayPicture());
        assertEquals(new BigDecimal("10.00"), productBean.getBasePrice());
        assertEquals(new BigDecimal("10.00"), productBean.getSellingPrice());
        assertEquals(new BigDecimal("0.00"), productBean.getSale());
        assertEquals(1, productItems.size());
        assertEquals(pictures.size(), productBean.getPictures().size());
    }

}
