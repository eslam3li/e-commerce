package com.jets.ecommerce.controller.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.util.NumberParser;


@WebServlet(urlPatterns = {"/product-detail"})
public class ProductDetailServlet extends EcommerceBaseServlet {
	
	ProductsService productsService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		productsService = getServicesProvider().getProductsService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("productId");
		if(id != null) {
			int productId = NumberParser.parseInt(id, -1);
			if(productId > 0) {
				ProductBean productBean = productsService.getProduct(productId);
				if(productBean != null) {
					req.setAttribute("product", productBean);
					req.getRequestDispatcher("product-detail.jsp").forward(req, resp);
					return;
				}
			}
		}
		resp.sendRedirect("error.html");
	}

}
