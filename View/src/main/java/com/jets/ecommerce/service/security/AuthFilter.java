/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    private final String CURRENT_USER = "AuthFilter.CURRENT_USER";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpSession session = httpServletRequest.getSession(true);
                SecurityContext.setUser((User) session.getAttribute(CURRENT_USER));
            }
            chain.doFilter(request, response);
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpSession session = httpServletRequest.getSession(true);
                session.setAttribute(CURRENT_USER, SecurityContext.getUser());
            }
        } finally {
            SecurityContext.clear();
        }
    }

    @Override
    public void destroy() {

    }

}
