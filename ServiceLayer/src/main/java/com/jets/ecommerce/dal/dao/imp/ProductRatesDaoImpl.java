package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.*;
import com.jets.ecommerce.dal.entity.ProductRate;
import com.jets.ecommerce.dal.entity.ProductRateId;
import org.hibernate.SessionFactory;


public class ProductRatesDaoImpl extends GenericDaoImpl<ProductRate, ProductRateId> implements ProductRatesDao {

    public ProductRatesDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
