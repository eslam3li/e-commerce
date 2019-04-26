/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;


public interface CategoriesService {

    List<CategoryBean> getAllCategories();

    List<CategoryBean> getTopLevelCategories();

    /**
     * Parent category is null
     *
     * @param parentCategoryId
     * @return
     */
    List<CategoryBean> getSubCategories(int parentCategoryId);

    /**
     * Parent category is filled all the way up
     *
     * @param categoryId
     * @return
     */
    CategoryBean getCategory(int categoryId);

    /**
     * Parent category in bean is ignored
     *
     * @param parentCategoryId
     * @param categoryBean
     */
    @Access(Admin.class)
    void addCategory(Integer parentCategoryId, CategoryBean categoryBean);

    @Access(Admin.class)
    void removeCategory(int categoryId);

    @Access(Admin.class)
    void updateCategory(CategoryBean categoryBean);
}
