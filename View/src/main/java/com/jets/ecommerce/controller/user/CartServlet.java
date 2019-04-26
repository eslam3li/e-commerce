package com.jets.ecommerce.controller.user;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.OrdersService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.PromocodesService;
import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.service.exceptions.CodeAlreadyUsedException;
import com.jets.ecommerce.service.exceptions.OrderException;
import com.jets.ecommerce.service.security.SecurityContext;
import com.jets.ecommerce.util.JsonAdapter;


@WebServlet(urlPatterns = { "/cart" })
public class CartServlet extends EcommerceBaseServlet {
	
	OrdersService ordersService;
	PromocodesService promocodesService;
	AuthService authService;
	ProductsService productsService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ordersService = getServicesProvider().getOrdersService();
		promocodesService = getServicesProvider().getPromocodesService();
		authService = getServicesProvider().getAuthService();
		productsService = getServicesProvider().getProductsService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(!SecurityContext.isClient()) {
			resp.setStatus(401);
			resp.getWriter().write("you must be logged in");
			return;
		}
		
		String operation = req.getParameter("operation");
		JsonAdapter jsonAdapter = new JsonAdapter();
		
		if(operation != null) {
			
			HttpSession httpSession = req.getSession(true);
			Set<CartItemBean> cartItems = (Set<CartItemBean>) httpSession.getAttribute("cart");
			if(cartItems == null) {
				cartItems = new HashSet<>();
			}
			
			switch(operation) {
				case("add"):
					String cartItem = req.getParameter("cartItem");
					CartItemBean cartItemBean = jsonAdapter.fromJson(cartItem, CartItemBean.class);
					if(cartItemBean != null) {
						cartItems.remove(cartItemBean);
						cartItems.add(cartItemBean);
						httpSession.setAttribute("cart", cartItems);
						return;
					}
					else {
						String couponCode = req.getParameter("coupon");
						if(couponCode != null) {
							PromocodeBean promocodeBean;
							try {
								promocodeBean = promocodesService.findByCode(couponCode);
								if(promocodeBean == null) {
									resp.setStatus(404);
								}
								else {
									httpSession.setAttribute("promocode", promocodeBean);
									resp.getWriter().write(jsonAdapter.toJson(promocodeBean, PromocodeBean.class, null));
								}
							} catch (CodeAlreadyUsedException e) {
								resp.setStatus(404);
								resp.getWriter().write(e.getMessage());
							}
							return;
						}
					}
					break;
				case("remove"):
					String id = req.getParameter("productItemId");
					if(id != null) {
						cartItems = cartItems.stream().filter(item -> !id.equals(item.getProductItemBean().getId())).collect(Collectors.toSet());
						httpSession.setAttribute("cart", cartItems);
						return;
					}
			}
		}
		
		req.getRequestDispatcher("error.html").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operation = req.getParameter("operation");
		
		if(operation != null) {
			
			HttpSession httpSession = req.getSession(true);
			Set<CartItemBean> cartItems = (Set<CartItemBean>) httpSession.getAttribute("cart");
			
			if(cartItems != null) {
				switch(operation) {
					case("pay"):
						if(SecurityContext.isClient()) {
							PromocodeBean promocode = (PromocodeBean) httpSession.getAttribute("promocode");
							try {
								ordersService.makeOrder(promocode, cartItems);
							} catch (OrderException e) {
								httpSession.setAttribute("checkoutError", e.getMessage());
								resp.sendRedirect("home");
								return;
							}
							httpSession.setAttribute("thankYou", true);
						}
						else {
							resp.setStatus(401);
							resp.getWriter().write("you must be logged in");
							return;
						}
					case("cancel"):
						httpSession.removeAttribute("cart");
						httpSession.removeAttribute("promocode");
						resp.sendRedirect("home");
						return;
				}
			}
			
		}
		
		resp.sendRedirect("home");
		
	}

}
