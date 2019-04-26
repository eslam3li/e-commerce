/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.PromocodesService;
import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.util.ParametersReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(urlPatterns = {"/admin/promocdoes"})
public class PromoServlet extends EcommerceBaseServlet {

    PromocodesService promocodesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        promocodesService = getServicesProvider().getPromocodesService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ParametersReader reader = new ParametersReader(request);
            int id = reader.getInteger("delete", -1);
            if (id != -1) {
                promocodesService.removePromocode(new PromocodeBean(id));
                response.sendRedirect(request.getRequestURI());
            } else {
                List<PromocodeBean> promocodes = promocodesService.getAllPromocodes(0, 20);
                request.setAttribute("promocodes", promocodes);
                request.getRequestDispatcher("/WEB-INF/adminpages/promocodes.jsp")
                        .forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ParametersReader reader = new ParametersReader(request);
            String code = reader.getString("code");
            int discount = reader.getInteger("discount", 0);
            PromocodeBean promocode
                    = new PromocodeBean(code, BigDecimal.valueOf(discount));
            promocodesService.addPromocode(promocode);
            response.sendRedirect(request.getRequestURI());
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        }
    }

}
