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
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.beans.filters.PatternFilter;
import com.jets.ecommerce.service.beans.filters.UsersFilter;
import com.jets.ecommerce.util.ParametersReader;

@WebServlet(name = "UsersServlet", urlPatterns = {"/admin/users"})
public class UsersServlet extends EcommerceBaseServlet {

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
            ParametersReader reader = new ParametersReader(request);
            PatternFilter name = getFilter(reader.getString("name"));
            PatternFilter email = getFilter(reader.getString("email"));
            PatternFilter phone = getFilter(reader.getString("phone"));
            UsersFilter filter = new UsersFilter(name, email, phone);
            int pageSize = 10;
            int start = (reader.getInteger("page", 1) - 1) * pageSize;
            List<UserBean> users = authService.filterUsers(filter, start, pageSize);
            int usersCount = authService.getUsersCount(filter);
            request.setAttribute("users", users);
            request.setAttribute("usersCount", usersCount);
            request.setAttribute("pageCount", (usersCount + pageSize - 1) / pageSize);
            request.getRequestDispatcher("/WEB-INF/adminpages/users.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private PatternFilter getFilter(String text) {
        return text == null ? null : new PatternFilter(text);
    }

}
