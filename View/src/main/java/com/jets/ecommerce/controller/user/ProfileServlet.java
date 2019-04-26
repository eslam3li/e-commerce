/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.user;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.RechargeCardBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.exceptions.LoginException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends EcommerceBaseServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        System.out.println(code);
        RechargeCardBean rechargeCardBean = new RechargeCardBean();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userDetails = new UserBean();
//        usertest.setName("Sara");
//        usertest.setBalance(new BigDecimal(100));
//
//        session.setAttribute("mail", "yahoo");
//        session.setAttribute("password", "1234");
        HttpSession session = req.getSession(false);
        try {
            UserBean userBean = authService.loginUser((String) session.getAttribute("mail"), (String) session.getAttribute("password"));
            req.setAttribute("user", userDetails);
            long userAge = new Date().getTime() - userBean.getBirthDate().getTime();
            req.setAttribute("user", userAge);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);

        } catch (LoginException ex) {
            ex.printStackTrace();
        }

    }

}
