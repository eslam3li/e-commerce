/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jets.ecommerce.EcommerceBaseServlet;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.service.beans.filters.PatternFilter;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import com.jets.ecommerce.service.beans.filters.Range;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "ShowProductsServlet", urlPatterns = {"/admin/showproducts"})
public class ShowProductsServlet extends EcommerceBaseServlet {

    private static final int PAGE_SIZE = 20;

    private ProductsService productsService;
    private CategoriesService categoriesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productsService = getServicesProvider().getProductsService();
        categoriesService = getServicesProvider().getCategoriesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsFilter productsFilter = null;
        if (req.getParameterNames().hasMoreElements()) {
            Integer priceMin = parseNumber(req.getParameter("priceMin"), null);
            Integer priceMax = parseNumber(req.getParameter("priceMax"), null);
            Integer categoryId = parseNumber(req.getParameter("categoryId"), -1);
            productsFilter = new ProductsFilter();
            String name = req.getParameter("name");
            if (name != null && !name.trim().isEmpty()) {
                productsFilter.setNameFilter(new PatternFilter(name));
            }
            productsFilter.setPriceRange(new Range<Integer>(priceMin, priceMax));
            if (categoryId != -1) {
                productsFilter.setCategoryId(categoryId);
            }
        }
        int pageSize = 10;
        int start = (parseNumber(req.getParameter("page"), 1) - 1) * pageSize;
        req.setAttribute("products", productsService.searchProducts(productsFilter, start, pageSize));
        int productsCount = productsService.getProductsCount(productsFilter);
        req.setAttribute("productsCount", productsCount);
        req.setAttribute("pageCount", (productsCount + pageSize - 1) / pageSize);
        List<CategoryBean> categories = categoriesService.getAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/adminpages/products/show.jsp").forward(req, resp);
    }

    private Integer parseNumber(String number, Integer defaultValue) {
        Integer num;
        try {
            num = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            num = defaultValue;
        }
        return num;
    }

}
