package com.jets.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.beans.filters.PatternFilter;
import com.jets.ecommerce.service.beans.filters.UsersFilter;
import com.jets.ecommerce.util.ParametersReader;


@WebServlet(name = "AdminsServlet", urlPatterns = {"/admin/admins"})
public class AdminsServlet extends EcommerceBaseServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = getServicesProvider().getAuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<AdminBean> admins = authService.getAllAdmins();
            request.setAttribute("admins", admins);
            request.getRequestDispatcher("/WEB-INF/adminpages/admins.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private PatternFilter getFilter(String text) {
        return text == null ? null : new PatternFilter(text);
    }

}
