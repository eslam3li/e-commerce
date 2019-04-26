/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import java.util.List;


public interface ProductsDao extends GenericDao<Product, Integer> {

    List<Product> findByCategory(Category category);

    List<Product> findByFilter(ProductsFilter filter, Page page);

    public int getProductsCount(ProductsFilter filter);

}
