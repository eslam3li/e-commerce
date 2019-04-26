/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.beans.OrderBean;
import com.jets.ecommerce.service.beans.OrderItemBean;
import com.jets.ecommerce.service.beans.OrderState;
import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.service.beans.filters.OrdersFilter;
import com.jets.ecommerce.service.exceptions.OrderException;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.User;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;
import java.util.Set;


public interface OrdersService {

    /**
     * used by user to make order out of cart items
     *
     * @param promocode null if no promocode is used
     */
    @Access(Client.class)
    void makeOrder(PromocodeBean promocode, Set<CartItemBean> cartItems) throws OrderException;

    /**
     * used by the administrator to change order state for tracking by the user;
     *
     * @param orderId
     * @param state
     */
    @Access(Admin.class)
    void changeOrderState(int orderId, OrderState state);

    /**
     * returned bean have user set to null since it refer to the current user
     *
     * @return
     */
    @Access(Client.class)
    List<OrderBean> getMyOrders();

    /**
     * used by administrator to paginate throw the list of all orders. returned
     * beans have user set correctly
     *
     * @param filter
     * @param start the index of the first item in the page
     * @param pageSize
     * @return the returned list has a length of all users matching the search
     * result don't try to access any elements outside the range start to
     * start+pagesize-1
     */
    @Access(Admin.class)
    List<OrderBean> getAllOrders(OrdersFilter filter, int start, int pageSize);

    @Access(Admin.class)
    int getOrdersCount(OrdersFilter filter);

    // todo: provide two versions one for admin and one for client and check
    // that order belongs to the client
    @Access(User.class)
    OrderBean getOrder(int orderId);

    // todo: provide two versions one for admin and one for client and check
    // that order belongs to the client
    @Access(User.class)
    List<OrderItemBean> getOrderItems(int orderId);
}
