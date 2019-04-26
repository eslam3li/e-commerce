
package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.ProductRatesDao;
import com.jets.ecommerce.dal.dao.ProductsDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.ProductRate;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.dal.entity.adapter.ProductAdapter;
import com.jets.ecommerce.dal.entity.adapter.ProductItemAdapter;
import com.jets.ecommerce.dal.entity.adapter.ProductRateAdapter;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.service.beans.ProductRateBean;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import com.jets.ecommerce.service.security.SecurityContext;

import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;


public class ProductsServiceImpl implements ProductsService {

    private final ProductsDao productsDao;
    private final ProductItemsDao productItemsDao;
    private final ProductRatesDao productRatesDao;
    private final CategoriesDao categoriesDao;
    private final UsersDao usersDao;

    public ProductsServiceImpl(DaosFactory daosFactory) {
        this.productsDao = daosFactory.getProductsDao();
        this.productItemsDao = daosFactory.getProductItemsDao();
        this.productRatesDao = daosFactory.getProductRatesDao();
        this.categoriesDao = daosFactory.getCategoriesDao();
        this.usersDao = daosFactory.getUsersDao();
    }

    @Override
    public List<ProductBean> searchProducts(ProductsFilter filter, int start, int pageSize) {
        return productsDao.findByFilter(filter, new Page(start, pageSize))
                .stream()
                .map(ProductAdapter::toBean)
                .collect(toList());
    }

    @Override
    public int getProductsCount(ProductsFilter productsFilter) {
        return productsDao.getProductsCount(productsFilter);
    }

    @Override
    public ProductBean getProduct(int productId) {
        Product product = productsDao.get(productId);
        return ProductAdapter.toBean(product);
    }

    @Override
    public List<ProductItemBean> getProductItems(int productId) {
        Product product = productsDao.load(productId);
        return productItemsDao.findByProduct(product).stream()
                .map(ProductItemAdapter::toBean)
                .collect(toList());
    }

    @Override
    public List<ProductRateBean> getProductRates(int productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void rateProduct(ProductRateBean productRateBean) {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        Product product = productsDao.load(productRateBean.getProduct().getId());
        ProductRate productRate = ProductRateAdapter.fromBean(productRateBean, user, product);
        productRatesDao.saveOrUpdate(productRate);
    }

    @Override
    public void addProduct(int categoryId, ProductBean productBean, Set<ProductItemBean> productItemBeans) {
        Category category = categoriesDao.load(categoryId);
        Product product = ProductAdapter.fromBean(productBean, category);
        productsDao.save(product);
        productItemBeans.forEach((t) -> {
            ProductItem item = ProductItemAdapter.fromBean(t, product);
            productItemsDao.save(item);
        });
    }

    @Override
    public void removeProduct(int productId) {
        Product product = productsDao.load(productId);
        productsDao.delete(product);
    }

    @Override
    public void updateProduct(ProductBean productBean, Set<ProductItemBean> productItemBeans) {
        int categoryId = getProduct(productBean.getId()).getCategory().getId();
        Category category = categoriesDao.load(categoryId);
        Product product = ProductAdapter.fromBean(productBean, category);
        productsDao.update(product);
        productItemsDao.removeAllByProduct(product);
        productItemBeans.forEach((t) -> {
            ProductItem item = ProductItemAdapter.fromBean(t, product);
            productItemsDao.save(item);
        });
    }

}
