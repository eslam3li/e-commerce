/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import java.util.List;


public interface ProductItemsDao extends GenericDao<ProductItem, Integer> {

    List<ProductItem> findByProduct(Product product);

    void removeAllByProduct(Product product);

}
