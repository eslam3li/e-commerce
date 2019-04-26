
package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.adapter.CategoryAdapter;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.beans.CategoryBean;

import java.util.List;
import static java.util.stream.Collectors.toList;


public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesDao categoriesDao;

    public CategoriesServiceImpl(DaosFactory daosFactory) {
        this.categoriesDao = daosFactory.getCategoriesDao();
    }

    @Override
    public List<CategoryBean> getAllCategories() {
        List<Category> allCategories = categoriesDao.findAll();
        return allCategories.stream()
                .map(CategoryAdapter::toBean)
                .collect(toList());
    }

    @Override
    public List<CategoryBean> getTopLevelCategories() {
        List<Category> allCategories = categoriesDao.findTopLevelCategories();
        return allCategories.stream()
                .map(CategoryAdapter::toBean)
                .collect(toList());
    }

    @Override
    public List<CategoryBean> getSubCategories(int parentCategoryId) {
        Category parent = categoriesDao.load(parentCategoryId);
        List<Category> allCategories = categoriesDao.findByParentCategory(parent);
        return allCategories.stream()
                .map(CategoryAdapter::toBean)
                .collect(toList());
    }

    @Override
    public CategoryBean getCategory(int categoryId) {
        Category category = categoriesDao.get(categoryId);
        return CategoryAdapter.toBean(category);
    }

    @Override
    public void addCategory(Integer parentCategoryId, CategoryBean categoryBean) {
        Category entity = CategoryAdapter.fromBean(categoryBean);
        if (parentCategoryId != null) {
            Category parent = categoriesDao.load(parentCategoryId);
            entity.setParentCategory(parent);
        }
        categoriesDao.save(entity);
    }

    @Override
    public void removeCategory(int categoryId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateCategory(CategoryBean categoryBean) {
        categoriesDao.update(CategoryAdapter.fromBean(categoryBean));
    }

}
