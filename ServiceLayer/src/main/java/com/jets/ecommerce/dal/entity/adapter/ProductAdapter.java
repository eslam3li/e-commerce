
package com.jets.ecommerce.dal.entity.adapter;

import java.util.Set;
import java.util.stream.Collectors;

import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;


public class ProductAdapter {

    public static Product fromBean(ProductBean bean, Category category) {
        if (bean == null) {
            return null;
        }
        Product product = new Product(bean.getName(), bean.getDescription(),
                bean.getDisplayPicture(), bean.getBasePrice(),
                bean.getSellingPrice(), category);
        product.setId(bean.getId());
        product.setSale(bean.getSale());
        product.getPictures().addAll(bean.getPictures());
        Set<ProductItem> productItems = bean.getProductItems().stream()
        		.map((productItem) -> {
        			return ProductItemAdapter.fromBean(productItem, product);
        		})
        		.collect(Collectors.toSet());
        product.getProductItems().addAll(productItems);
        return product;
    }

    public static ProductBean toBean(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductBean productBean = new ProductBean(entity.getId());
        productBean.setName(entity.getName());
        productBean.setDescription(entity.getDescription());
        productBean.setDisplayPicture(entity.getDisplayPicture());
        productBean.setCategory(CategoryAdapter.toBeanNoParent(entity.getCategory()));
        productBean.setBasePrice(entity.getBasePrice());
        productBean.setSellingPrice(entity.getSellingPrice());
        productBean.setSale(entity.getSale());
        productBean.getPictures().addAll(entity.getPictures());
        Set<ProductItemBean> productItems = entity.getProductItems().stream()
        		.map((productItem) -> {
        			Product product = productItem.getProduct();
        			productItem.setProduct(null);
        			ProductItemBean productItemBean = ProductItemAdapter.toBean(productItem);
        			productItem.setProduct(product);
        			return productItemBean;
        		})
        		.collect(Collectors.toSet());
        productBean.getProductItems().addAll(productItems);
        return productBean;
    }

}
