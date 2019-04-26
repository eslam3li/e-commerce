package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.CartItemsDao;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.CartItem;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.dal.entity.adapter.CartItemAdapter;
import com.jets.ecommerce.service.CartService;
import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.security.SecurityContext;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


public class CartServiceImpl implements CartService {

    private final CartItemsDao cartItemsDao;
    private final UsersDao usersDao;
    private final ProductItemsDao productItemsDao;

    public CartServiceImpl(DaosFactory daosFactory) {
        this.cartItemsDao = daosFactory.getCartItemsDao();
        this.usersDao = daosFactory.getUsersDao();
        this.productItemsDao = daosFactory.getProductItemsDao();
    }

    @Override
    public List<CartItemBean> getMyCartItems() {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        List<CartItem> cartItems = cartItemsDao.findByUser(user);
        return cartItems.stream().map(CartItemAdapter::toBean)
                .collect(toList());
    }

    @Override
    public void addCartItem(CartItemBean cartItemBean) {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        int productItemId = cartItemBean.getProductItemBean().getId();
        ProductItem productItem = productItemsDao.load(productItemId);
        CartItem cartItem = CartItemAdapter.fromBean(cartItemBean, user,
                productItem);
        cartItemsDao.save(cartItem);
    }
    
    @Override
    public void addCartItems(Set<CartItemBean> cartItemBeans) {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        for(CartItemBean cartItemBean : cartItemBeans) {
	        int productItemId = cartItemBean.getProductItemBean().getId();
	        ProductItem productItem = productItemsDao.load(productItemId);
	        CartItem cartItem = CartItemAdapter.fromBean(cartItemBean, user,
	                productItem);
	        cartItemsDao.save(cartItem);
        }
    }

    @Override
    public void removeProductItemFromCart(int productItemId) {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        ProductItem productItem = productItemsDao.load(productItemId);
        cartItemsDao.removeCartItem(user, productItem);
    }

    @Override
    public void updateCartItem(CartItemBean cartItemBean) {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        int productItemId = cartItemBean.getProductItemBean().getId();
        ProductItem productItem = productItemsDao.load(productItemId);
        CartItem cartItem = CartItemAdapter.fromBean(cartItemBean, user, productItem);
        cartItemsDao.update(cartItem);
    }

    @Override
    public void updateCart(List<CartItemBean> cartItemBeans) {
        clearCart();
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        cartItemBeans.forEach((cartItemBean) -> {
            int productItemId = cartItemBean.getProductItemBean().getId();
            ProductItem productItem = productItemsDao.load(productItemId);
            CartItem cartItem = CartItemAdapter.fromBean(cartItemBean,
                    user, productItem);
            cartItemsDao.save(cartItem);
        });
    }

    @Override
    public void clearCart() {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        cartItemsDao.removeAllForUser(user);
    }

}
