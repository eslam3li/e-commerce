/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.exceptions.LoginException;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/admin/login"})
public class LoginServlet extends EcommerceBaseServlet {

    AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            authService.logout();
            req.getSession(true).removeAttribute("adminBean");
            resp.sendRedirect(req.getContextPath() + "/admin/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/adminpages/login.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            AdminBean adminBean = authService.loginAdmin(email, password);
            req.getSession(true).setAttribute("adminBean", adminBean);
            String redirect = req.getParameter("redirect");
            if (redirect == null || redirect.isEmpty()) {
                resp.sendRedirect("dashboard");
            } else {
                resp.sendRedirect(redirect);
            }
        } catch (LoginException ex) {
            resp.sendRedirect("?error=true");
        }
    }

}
