package com.jets.ecommerce.controller.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.OrdersService;
import com.jets.ecommerce.service.beans.OrderBean;
import com.jets.ecommerce.service.beans.OrderItemBean;
import com.jets.ecommerce.service.beans.OrderState;
import java.util.List;


@WebServlet(name = "OrderDetailsServlet", urlPatterns = {"/admin/orderdetails"})
public class OrderDetailsServlet extends EcommerceBaseServlet {
    
    private OrdersService ordersService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ordersService = getServicesProvider().getOrdersService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderBean order = ordersService.getOrder(orderId);
            List<OrderItemBean> orderItems = ordersService.getOrderItems(orderId);
            request.setAttribute("states", OrderState.values());
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/WEB-INF/adminpages/orderdetails.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("orders");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderState orderState = OrderState.valueOf(request.getParameter("orderState"));
            ordersService.changeOrderState(orderId, orderState);
        } catch (Exception ex) {
        }
        response.sendRedirect(request.getRequestURI() + "?orderId=" + request.getParameter("orderId"));
    }
    
}
