/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.google.gson.reflect.TypeToken;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.ProductItemBean;
import com.jets.ecommerce.util.ParametersReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(name = "AddProductServlet", urlPatterns = {"/admin/addproduct"})
public class AddProductServlet extends EcommerceBaseServlet {
    
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
            int categoryId = paramReader.getInteger("categoryId", -1);
            List<CategoryBean> categories = categoriesService.getAllCategories();
            CategoryBean selectedCategory = categoriesService.getCategory(categoryId);
            
            req.setAttribute("categories", categories);
            req.setAttribute("selectedCategory", selectedCategory);
            req.getRequestDispatcher("/WEB-INF/adminpages/products/add.jsp").forward(req, resp);
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ParametersReader paramReader = new ParametersReader(req);
            int categoryId = paramReader.getInteger("categoryId", -1);
            String productName = paramReader.getString("productName");
            String description = paramReader.getString("description");
            BigDecimal basePrice = paramReader.getDecimal("basePrice");
            BigDecimal sellingPrice = paramReader.getDecimal("sellingPrice");
            BigDecimal sale = paramReader.getDecimal("sale");
            Type type = new TypeToken<List<ProductItemBeanJson>>() {
            }.getType();
            List<ProductItemBeanJson> productItemBeanJsons = paramReader
                    .getJsonList("productItemsJson", type);
            // Todo handle pictures and error
            String defaultPicture = "uploads/" + paramReader.saveFile("defaultPicture", "uploads");
            resp.getWriter().println(defaultPicture);
            List<String> pictures = paramReader.saveFiles("pictures", "uploads").stream()
                    .map((str) -> {
                        return "uploads/" + str;
                    }).collect(toList());
            resp.getWriter().println(pictures);
            Set<ProductItemBean> productItemBeans = productItemBeanJsons.stream()
                    .map((pj) -> {
                        return new ProductItemBean(pj.color, pj.quantity, null);
                    }).collect(Collectors.toSet());
            ProductBean productBean = new ProductBean(productName, description,
                    defaultPicture, basePrice, sellingPrice, null);
            productBean.getPictures().addAll(pictures);
            productBean.setSale(sale);
            productsService.addProduct(categoryId, productBean, productItemBeans);
            resp.sendRedirect("showproducts");
        } catch (Exception ex) {
            ex.printStackTrace(resp.getWriter());
            throw new ServletException(ex);
        }
    }
    
    static class ProductItemBeanJson {
        
        String color;
        Integer quantity;
        
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
