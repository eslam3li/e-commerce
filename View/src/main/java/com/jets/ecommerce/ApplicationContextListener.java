/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce;

import com.jets.ecommerce.service.ServicesProvider;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener()
public class ApplicationContextListener implements ServletContextListener {

    public static final String SERVICES_PROVIDER = "ServicesProvider";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServicesProvider servicesProvider = ServicesProvider.getInstance();
        sce.getServletContext().setAttribute(SERVICES_PROVIDER, servicesProvider);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServicesProvider servicesProvider = (ServicesProvider) sce.getServletContext()
                .getAttribute(SERVICES_PROVIDER);
        servicesProvider.goodBye();
    }
}
