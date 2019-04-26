/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.util.ParametersReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(name = "EditProductServlet", urlPatterns = {"/admin/editproduct"})
public class EditProductServlet extends EcommerceBaseServlet {

    ProductsService productsService;
    CategoriesService categoriesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productsService = getServicesProvider().getProductsService();
        categoriesService = getServicesProvider().getCategoriesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ParametersReader paramReader = new ParametersReader(req);
            int productId = paramReader.getInteger("productId", -1);
            ProductBean product = productsService.getProduct(productId);
            List<ProductItemBean> productItems = productsService.getProductItems(productId);

            List<ProductItemBeanJson> productItemBeanJsons = new LinkedList<>();
            productItems.forEach((productItem) -> {
                ProductItemBeanJson jsonItem
                        = new ProductItemBeanJson(productItem.getColor(),
                                productItem.getQuantityInStock());
                productItemBeanJsons.add(jsonItem);
            });
            req.setAttribute("product", product);
            req.setAttribute("productItemBeansJson", new Gson().toJson(productItemBeanJsons));
            req.getRequestDispatcher("/WEB-INF/adminpages/products/edit.jsp")
                    .forward(req, resp);
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ParametersReader paramReader = new ParametersReader(req);
            int productId = paramReader.getInteger("productId", -1);
            String productName = paramReader.getString("productName");
            String description = paramReader.getString("description");
            BigDecimal basePrice = paramReader.getDecimal("basePrice");
            BigDecimal sellingPrice = paramReader.getDecimal("sellingPrice");
            BigDecimal sale = paramReader.getDecimal("sale");
            Type type = new TypeToken<List<ProductItemBeanJson>>() {
            }.getType();
            resp.getWriter().println(paramReader.getString("productItemsJson"));
            List<ProductItemBeanJson> productItemBeanJsons = paramReader
                    .getJsonList("productItemsJson", type);
            // Todo handle pictures and error
            String defaultPicture = paramReader.saveFile("defaultPicture", "uploads");
            List<String> pictures = paramReader.saveFiles("pictures", "uploads");
            Set<ProductItemBean> productItemBeans = productItemBeanJsons.stream()
                    .map((pj) -> {
                        return new ProductItemBean(pj.color, pj.quantity, null);
                    }).collect(Collectors.toSet());
            ProductBean productBean = new ProductBean(productName, description,
                    defaultPicture, basePrice, sellingPrice, null);
            productBean.setId(productId);
            productBean.getPictures().addAll(pictures);
            productBean.setSale(sale);
            productsService.updateProduct(productBean, productItemBeans);
            resp.sendRedirect("showproducts");
        } catch (Exception ex) {
            ex.printStackTrace(resp.getWriter());
            throw new ServletException(ex);
        }
    }

    static class ProductItemBeanJson {

        String color;
        Integer quantity;

        public ProductItemBeanJson() {
        }

        public ProductItemBeanJson(String color, Integer quantity) {
            this.color = color;
            this.quantity = quantity;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

    }

}
