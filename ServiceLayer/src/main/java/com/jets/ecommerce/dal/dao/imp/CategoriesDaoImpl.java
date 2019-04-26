/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.entity.Category;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class CategoriesDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoriesDao {

    public CategoriesDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Category> findTopLevelCategories() {
        return (List<Category>) currentSession().createCriteria(daoType)
                .add(Restrictions.isNull("parentCategory"))
                .list();
    }

    @Override
    public List<Category> findByParentCategory(Category parent) {
        return (List<Category>) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("parentCategory", parent))
                .list();
    }

}
