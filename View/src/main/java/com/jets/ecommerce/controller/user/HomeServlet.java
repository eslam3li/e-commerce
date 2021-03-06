/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.user;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.controller.user.service.ProductServletGetter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends EcommerceBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	ProductServletGetter productServletGetter = new ProductServletGetter(getServicesProvider(), req);
		req.setAttribute("pages", productServletGetter.getPages());
		req.setAttribute("categories", productServletGetter.getCategories());
		req.setAttribute("products", productServletGetter.getProducts());
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

}
