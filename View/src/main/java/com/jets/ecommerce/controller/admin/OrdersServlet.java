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
import com.jets.ecommerce.service.beans.OrderState;
import com.jets.ecommerce.service.beans.filters.OrdersFilter;
import com.jets.ecommerce.service.beans.filters.PatternFilter;
import com.jets.ecommerce.util.ParametersReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(name = "OrdersServlet", urlPatterns = {"/admin/orders"})
public class OrdersServlet extends EcommerceBaseServlet {

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
            ParametersReader reader = new ParametersReader(request);
            PatternFilter username = getFilter(reader.getString("username"));
            PatternFilter email = getFilter(reader.getString("email"));
            OrderState orderState = null;
            try {
                orderState = OrderState.valueOf(reader.getString("orderState"));
            } catch (Exception ex) {

            }
            OrdersFilter filter = new OrdersFilter(username, email, orderState);
            int pageSize = 10;
            int start = (reader.getInteger("page", 1) - 1) * pageSize;
            List<OrderBean> orders = ordersService.getAllOrders(filter, start, pageSize);
            int ordersCount = ordersService.getOrdersCount(filter);
            request.setAttribute("orders", orders);
            request.setAttribute("ordersCount", ordersCount);
            request.setAttribute("pageCount", (ordersCount + pageSize - 1) / pageSize);
            request.setAttribute("states", OrderState.values());
            request.getRequestDispatcher("/WEB-INF/adminpages/orders.jsp")
                    .forward(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(OrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PatternFilter getFilter(String text) {
        return text == null ? null : new PatternFilter(text);
    }

}
