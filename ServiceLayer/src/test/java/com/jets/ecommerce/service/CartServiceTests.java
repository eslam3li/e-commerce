/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.EcommerceDBTestContext;
import com.jets.ecommerce.dal.cfg.DummyData;
import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.service.impl.ServicesProviderImpl;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CartServiceTests extends EcommerceDBTestContext {

    CartService cartService;
    AuthService authService;
    ProductsService productsService;

    @BeforeEach
    void init() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        ServicesProvider servicesProvider = new ServicesProviderImpl(sessionFactory);
        cartService = servicesProvider.getCartService();
        authService = servicesProvider.getAuthService();
        productsService = servicesProvider.getProductsService();
        DummyData.init(getSessionFactory()).insertUsers();
        authService.loginUser("ib.yousre@gmail.com", "hima");
    }

    @AfterEach
    void close() {
        authService.logout();
        closeSessionFactory();
    }

    @Test
    void test_add_cart_item_and_get_mycart() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        CartItemBean item = new CartItemBean(productItemBeans.get(0), 5);
        cartService.addCartItem(item);
        Assertions.assertEquals(1, cartService.getMyCartItems().size());
    }

    @Test
    void test_remove_cart_item() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        CartItemBean item = new CartItemBean(productItemBeans.get(0), 5);
        cartService.addCartItem(item);
        Assertions.assertEquals(1, cartService.getMyCartItems().size());
        cartService.removeProductItemFromCart(productItemBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
    }

    @Test
    void test_update_cart_item() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        CartItemBean item = new CartItemBean(productItemBeans.get(0), 5);
        cartService.addCartItem(item);
        Assertions.assertEquals(1, cartService.getMyCartItems().size());

        List<CartItemBean> myCartItems = cartService.getMyCartItems();
        Assertions.assertEquals(5, myCartItems.get(0).getQuantity());
        myCartItems.get(0).setQuantity(100);
        cartService.updateCartItem(myCartItems.get(0));
        Assertions.assertEquals(1, cartService.getMyCartItems().size());
        Assertions.assertEquals(100, cartService.getMyCartItems().get(0).getQuantity());
    }

    @Test
    void test_update_cart_1() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        CartItemBean item = new CartItemBean(productItemBeans.get(0), 5);
        cartService.addCartItem(item);
        Assertions.assertEquals(1, cartService.getMyCartItems().size());

        List<CartItemBean> myCartItems = cartService.getMyCartItems();
        myCartItems.clear();
        cartService.updateCart(myCartItems);
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
    }

    @Test
    void test_update_cart_2() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        CartItemBean item = new CartItemBean(productItemBeans.get(0), 5);
        cartService.addCartItem(item);
        Assertions.assertEquals(1, cartService.getMyCartItems().size());

        List<CartItemBean> myCartItems = cartService.getMyCartItems();
        myCartItems.clear();
        myCartItems.add(new CartItemBean(productItemBeans.get(0), 5));
        myCartItems.add(new CartItemBean(productItemBeans.get(1), 5));
        cartService.updateCart(myCartItems);
        Assertions.assertEquals(2, cartService.getMyCartItems().size());
    }

    @Test
    void test_clear_cart() throws Exception {
        DummyData.init(getSessionFactory()).insertProducts();
        List<ProductBean> productBeans = productsService.searchProducts(null, 0, 10);
        List<ProductItemBean> productItemBeans
                = productsService.getProductItems(productBeans.get(0).getId());
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
        cartService.addCartItem(new CartItemBean(productItemBeans.get(0), 5));
        cartService.addCartItem(new CartItemBean(productItemBeans.get(1), 5));
        Assertions.assertEquals(2, cartService.getMyCartItems().size());
        cartService.clearCart();
        Assertions.assertEquals(0, cartService.getMyCartItems().size());
    }

}
