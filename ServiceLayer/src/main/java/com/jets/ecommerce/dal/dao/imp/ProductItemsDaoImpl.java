/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class ProductItemsDaoImpl extends GenericDaoImpl<ProductItem, Integer> implements ProductItemsDao {

    public ProductItemsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<ProductItem> findByProduct(Product product) {
        return currentSession().createCriteria(daoType)
                .add(Restrictions.eq("product", product))
                .list();
    }

    @Override
    public void removeAllByProduct(Product product) {
        currentSession().createQuery("delete from product_items where product = :product")
                .setParameter("product", product)
                .executeUpdate();
    }

}
