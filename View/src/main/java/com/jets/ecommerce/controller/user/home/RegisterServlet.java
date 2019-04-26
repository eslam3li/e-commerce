package com.jets.ecommerce.controller.user.home;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.exceptions.RegisterationException;
import java.math.BigDecimal;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends EcommerceBaseServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String birthdate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        UserBean userBean = new UserBean(userName, email, password);
        userBean.setBalance(BigDecimal.ZERO);
        try {
            authService.addUser(userBean);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (RegisterationException e) {
            response.sendRedirect(request.getRequestURI());
            throw new ServletException(e);
        }
    }

}
