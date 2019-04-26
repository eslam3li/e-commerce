/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce;

import com.jets.ecommerce.service.ServicesProvider;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public abstract class EcommerceBaseServlet extends HttpServlet {

    private ServicesProvider servicesProvider;

    /**
     * All subclasses must call this method
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servicesProvider = (ServicesProvider) config.getServletContext()
                .getAttribute(ApplicationContextListener.SERVICES_PROVIDER);
    }

    /**
     * Use this to get access to service provider
     *
     * @return
     */
    protected ServicesProvider getServicesProvider() {
        return servicesProvider;
    }

}
