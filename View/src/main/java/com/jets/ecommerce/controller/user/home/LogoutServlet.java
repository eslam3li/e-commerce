package com.jets.ecommerce.controller.user.home;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.CartService;
import com.jets.ecommerce.service.beans.CartItemBean;


@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class LogoutServlet extends EcommerceBaseServlet {

    private static final long serialVersionUID = 1L;
    
    private CartService cartService;

    public LogoutServlet() {
        super();
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cartService = getServicesProvider().getCartService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
		Set<CartItemBean> cartItemBeans = (Set<CartItemBean>) request.getSession(true).getAttribute("cart");
        if(cartItemBeans != null) {
        	cartService.clearCart();
			cartService.addCartItems(cartItemBeans);
		}
        session.invalidate();
		request.getSession(true).setAttribute("loggedOut", true);
		response.sendRedirect("home");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
