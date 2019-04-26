/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.util.ParametersReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


@WebServlet(urlPatterns = {"/admin/categories"})
public class CategoriesServlet extends EcommerceBaseServlet {

    CategoriesService categoriesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoriesService = getServicesProvider().getCategoriesService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ParametersReader paramReader = new ParametersReader(request);
            int categoryId = paramReader.getInteger("categoryId", -1);
            List<CategoryBean> categories = categoriesService.getAllCategories();
            CategoryBean selectedCategory = categoriesService.getCategory(categoryId);

            request.setAttribute("categories", categories);
            request.setAttribute("selectedCategory", selectedCategory);
            request.getRequestDispatcher("/WEB-INF/adminpages/addcategory.jsp").forward(request, response);
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ParametersReader reader = new ParametersReader(req);
            String categoryName = reader.getString("categoryName");
            int parentCategoryId = reader.getInteger("categoryId", -1);
            String defaultPicture = reader.saveFile("defaultPicture", "uploads");
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setName(categoryName);
            categoriesService.addCategory(parentCategoryId, categoryBean);
            resp.sendRedirect(req.getRequestURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
