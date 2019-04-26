package com.jets.ecommerce.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.controller.user.service.ProductServletGetter;


@WebServlet(urlPatterns = { "/products" })
public class ProductsServlet extends EcommerceBaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductServletGetter productServletGetter = new ProductServletGetter(getServicesProvider(), req);
		PrintWriter out = resp.getWriter();
		out.write(productServletGetter.getAll());
	}

}
