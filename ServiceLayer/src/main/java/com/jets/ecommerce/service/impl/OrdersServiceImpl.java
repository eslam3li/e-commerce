
package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.OrderItemsDao;
import com.jets.ecommerce.dal.dao.OrdersDao;
import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.PromocodesDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.CartItem;
import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.OrderItem;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.Promocode;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.dal.entity.adapter.CartItemAdapter;
import com.jets.ecommerce.dal.entity.adapter.OrderAdapter;
import com.jets.ecommerce.dal.entity.adapter.OrderItemAdapter;
import com.jets.ecommerce.dal.entity.adapter.PromocodeAdapter;
import com.jets.ecommerce.service.OrdersService;
import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.beans.OrderBean;
import com.jets.ecommerce.service.beans.OrderItemBean;
import com.jets.ecommerce.service.beans.OrderState;
import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.service.beans.filters.OrdersFilter;
import com.jets.ecommerce.service.exceptions.OrderException;
import com.jets.ecommerce.service.security.SecurityContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;


public class OrdersServiceImpl implements OrdersService {

    private final OrdersDao ordersDao;
    private final OrderItemsDao orderItemsDao;
    private final ProductItemsDao productItemsDao;
    private final UsersDao usersDao;
    private final PromocodesDao promocodesDao;

    public OrdersServiceImpl(DaosFactory daosFactory) {
        this.ordersDao = daosFactory.getOrdersDao();
        this.orderItemsDao = daosFactory.getOrderItemsDao();
        this.productItemsDao = daosFactory.getProductItemsDao();
        this.usersDao = daosFactory.getUsersDao();
        this.promocodesDao = daosFactory.getPromocodesDao();
    }

    @Override
    public void makeOrder(PromocodeBean promocode, Set<CartItemBean> cartItems) throws OrderException {
    	BigDecimal total = BigDecimal.ZERO;
		for(CartItemBean cartItemBean : cartItems) {
			ProductItem productItem = productItemsDao.get(cartItemBean.getProductItemBean().getId());
			if(productItem.getQuantityInStock() < cartItemBean.getQuantity()) {
				throw new OrderException("Not enough products in store for product " + productItem.getProduct().getName() + " " + productItem.getColor());
			}
			total = total.add(productItem.getProduct().getSellingPrice());
		}
		Promocode promo = promocodesDao.findByCode(promocode.getCode());
		if(promo != null) {
			total = total.subtract(promo.getDiscount());
		}
		User user = usersDao.get(SecurityContext.getCurrentUserId());
		if(user.getBalance().compareTo(total) < 0) {
			throw new OrderException("Not enough balance");
		}
        Order order = new Order(new Date(), OrderState.PENDING, user);
        order.setPromoCode(promo);
        ordersDao.save(order);
        cartItems.forEach((cartItemBean) -> {
        	ProductItem productItem = productItemsDao.load(cartItemBean.getProductItemBean().getId());
        	CartItem cartItem = CartItemAdapter.fromBean(cartItemBean, user, productItem);
            OrderItem orderItem = new OrderItem(order, cartItem.getItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemsDao.saveOrUpdate(orderItem);
        });
        user.setBalance(user.getBalance().subtract(total));
    }

    @Override
    public void changeOrderState(int orderId, OrderState state) {
        Order order = ordersDao.load(orderId);
        order.setState(state);
        ordersDao.update(order);
    }

    @Override
    public List<OrderBean> getMyOrders() {
        int userId = SecurityContext.getCurrentUserId();
        User user = usersDao.load(userId);
        return ordersDao.findByUser(user, null).stream()
                .map(OrderAdapter::toBean)
                .collect(toList());
    }

    @Override
    public List<OrderBean> getAllOrders(OrdersFilter filter, int start, int pageSize) {
        return ordersDao.findByFilter(filter, new Page(start, pageSize))
                .stream().map(OrderAdapter::toBean)
                .collect(toList());
    }

    @Override
    public int getOrdersCount(OrdersFilter filter) {
        return ordersDao.getOrdersCount(filter);
    }

    @Override
    public OrderBean getOrder(int orderId) {
        return OrderAdapter.toBean(ordersDao.get(orderId));
    }

    @Override
    public List<OrderItemBean> getOrderItems(int orderId) {
        Order order = ordersDao.load(orderId);
        return orderItemsDao.findByOrder(order).stream()
                .map(OrderItemAdapter::toBean)
                .collect(toList());
    }

}
