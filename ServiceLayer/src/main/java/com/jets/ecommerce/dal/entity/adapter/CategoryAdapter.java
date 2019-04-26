
package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.service.beans.CategoryBean;


public class CategoryAdapter {

    public static Category fromBean(CategoryBean bean) {
        if (bean == null) {
            return null;
        }
        Category category = new Category(bean.getName());
        category.setPicture(bean.getPicture());
        category.setId(bean.getId());
        return category;
    }

    public static CategoryBean toBean(Category entity) {
        if (entity == null) {
            return null;
        }
        CategoryBean categoryBean = new CategoryBean(entity.getId());
        categoryBean.setName(entity.getName());
        categoryBean.setPicture(entity.getPicture());
        categoryBean.setParentCategory(toBean(entity.getParentCategory()));
        return categoryBean;
    }

    public static CategoryBean toBeanNoParent(Category entity) {
        if (entity == null) {
            return null;
        }
        CategoryBean categoryBean = new CategoryBean(entity.getId());
        categoryBean.setName(entity.getName());
        categoryBean.setPicture(entity.getPicture());
        return categoryBean;
    }

}
