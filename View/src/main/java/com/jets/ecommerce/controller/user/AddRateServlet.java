/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.user;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductRateBean;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AddRateServlet", urlPatterns = {"/rate"})
public class AddRateServlet extends EcommerceBaseServlet {

    private ProductsService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService = getServicesProvider().getProductsService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rate = Integer.parseInt(request.getParameter("rate"));
        int id = Integer.parseInt(request.getParameter("id"));
    
        String comment = request.getParameter("comment");
        ProductBean productBean = new ProductBean(id);
        ProductRateBean rateBean = new ProductRateBean(productBean, rate, comment);
        productService.rateProduct(rateBean);
        response.sendRedirect("product-detail.jsp");

    }

}
