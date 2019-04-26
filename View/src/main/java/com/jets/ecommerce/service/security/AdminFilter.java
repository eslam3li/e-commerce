/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.security;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (SecurityContext.isAdmin()
                || req.getServletPath().startsWith("/admin/login")
                || req.getServletPath().startsWith("/admin/css/")
                || req.getServletPath().startsWith("/admin/fonts/")
                || req.getServletPath().startsWith("/admin/images/")
                || req.getServletPath().startsWith("/admin/js/")
                || req.getServletPath().startsWith("/admin/vendor/")) {
            chain.doFilter(request, response);
        } else {
            String url = req.getRequestURI();
            if (req.getQueryString() != null) {
                String query = req.getQueryString().trim();
                if (!query.isEmpty()) {
                    url += "?" + query;
                }
            }
            resp.sendRedirect(req.getContextPath() + "/admin/login?redirect="
                    + URLEncoder.encode(url, "UTF-8"));
        }
    }

    @Override
    public void destroy() {

    }

}
