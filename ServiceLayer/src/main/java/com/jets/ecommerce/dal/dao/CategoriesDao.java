/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao;

import com.jets.ecommerce.dal.entity.Category;
import java.util.List;


public interface CategoriesDao extends GenericDao<Category, Integer> {

    List<Category> findTopLevelCategories();

    List<Category> findByParentCategory(Category parent);

}
