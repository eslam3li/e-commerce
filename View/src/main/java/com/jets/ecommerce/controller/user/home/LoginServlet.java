
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.user.home;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.CartService;
import com.jets.ecommerce.service.beans.CartItemBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.exceptions.LoginException;
import com.jets.ecommerce.util.JsonAdapter;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Loginservlet", urlPatterns = {"/login"})
public class LoginServlet extends EcommerceBaseServlet {

    AuthService authService;
    CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();
        cartService = getServicesProvider().getCartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            authService.logout();
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        try {
            UserBean userBean = authService.loginUser(email, password);
            HttpSession httpSession = req.getSession(true);
            JsonAdapter jsonAdapter = new JsonAdapter();
            httpSession.setAttribute("userBean", userBean);
            httpSession.setAttribute("loggedIn", true);
            httpSession.setAttribute("cartItems", jsonAdapter.toJson(cartService.getMyCartItems(), CartItemBean[].class, null));
            resp.sendRedirect("home");
        } catch (LoginException ex) {
            resp.sendRedirect("login?error");
        }
    }

}
