package com.jets.ecommerce.controller.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.exceptions.RegisterationException;


@WebServlet(name = "AddAdminServlet", urlPatterns = {"/admin/addadmin"})
public class AddAdminServlet extends EcommerceBaseServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/adminpages/addadmin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        AdminBean adminBean = new AdminBean(name, email, password, phone);
        try {
            authService.addAdmin(adminBean);
            response.sendRedirect("admins");
        } catch (RegisterationException e) {
            throw new ServletException(e);
        }
    }

}
