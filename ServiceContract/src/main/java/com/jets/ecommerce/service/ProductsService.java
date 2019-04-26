/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.service.beans.ProductRateBean;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;
import java.util.Set;


public interface ProductsService {

    /**
     * use to paginate throw the list of products matching the search. returned
     * bean has category set to null
     *
     * @param filter
     * @param start the index of the first item in the page
     * @param pageSize
     * @return the returned list has a length of all users matching the search
     * result don't try to access any elements outside the range start to
     * start+pagesize-1
     */
    List<ProductBean> searchProducts(ProductsFilter filter, int start, int pageSize);

    int getProductsCount(ProductsFilter productsFilter);

    ProductBean getProduct(int productId);

    List<ProductItemBean> getProductItems(int productId);

    List<ProductRateBean> getProductRates(int productId);

    @Access(Client.class)
    void rateProduct(ProductRateBean productRateBean);

    @Access(Admin.class)
    void addProduct(int categoryId, ProductBean productBean, Set<ProductItemBean> productItemBeans);

    @Access(Admin.class)
    void removeProduct(int productId);

    @Access(Admin.class)
    void updateProduct(ProductBean productBean, Set<ProductItemBean> productItemBeans);

}
