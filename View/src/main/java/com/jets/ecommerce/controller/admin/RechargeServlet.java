/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.RechargeCardsService;
import com.jets.ecommerce.service.beans.RechargeCardBean;
import com.jets.ecommerce.util.ParametersReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(urlPatterns = {"/admin/rechargecards"})
public class RechargeServlet extends EcommerceBaseServlet {

    RechargeCardsService rechargeCardsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        rechargeCardsService = getServicesProvider().getRechargeCardsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ParametersReader reader = new ParametersReader(request);
            List<RechargeCardBean> cards = rechargeCardsService.getAllUnusedCards();
            request.setAttribute("cards", cards);
            request.getRequestDispatcher("/WEB-INF/adminpages/rechargecards.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ParametersReader reader = new ParametersReader(request);
            int number = reader.getInteger("number", 0);
            int amount = reader.getInteger("amount", 0);
            rechargeCardsService.addRechargeCards(number, amount);
            response.sendRedirect(request.getRequestURI());
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        }
    }

}
