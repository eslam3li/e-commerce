package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductRate;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.ProductRateBean;


public class ProductRateAdapter {

    public static ProductRate fromBean(ProductRateBean bean, User user, Product product) {
        if (bean == null) {
            return null;
        }
        ProductRate productRate = new ProductRate(bean.getRate(), user, product);
        productRate.setComment(bean.getComment());
        return productRate;
    }

    public static ProductRateBean toBean(ProductRate entity) {
        if (entity == null) {
            return null;
        }
        ProductRateBean productRateBean = new ProductRateBean();
        productRateBean.setUser(UserAdapter.toBean(entity.getUser()));
        productRateBean.setProduct(ProductAdapter.toBean(entity.getProduct()));
        productRateBean.setRate(entity.getRate());
        productRateBean.setComment(entity.getComment());
        return productRateBean;
    }

}
