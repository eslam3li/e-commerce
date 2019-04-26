package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.service.beans.ProductItemBean;


public class ProductItemAdapter {

    public static ProductItem fromBean(ProductItemBean bean, Product product) {
        if (bean == null) {
            return null;
        }
        ProductItem productItem = new ProductItem(bean.getColor(),
                bean.getQuantityInStock(), product);
        return productItem;
    }

    public static ProductItemBean toBean(ProductItem entity) {
        if (entity == null) {
            return null;
        }
        ProductItemBean productItemBean = new ProductItemBean(entity.getId());
        productItemBean.setColor(entity.getColor());
        productItemBean.setQuantityInStock(entity.getQuantityInStock());
        productItemBean.setProduct(ProductAdapter.toBean(entity.getProduct()));
        return productItemBean;
    }

}
