
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.ProductsDao;
import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import com.jets.ecommerce.service.beans.filters.Sort;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ProductsDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductsDao {

    public ProductsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return (List<Product>) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("category", category))
                .list();
    }

    @Override
    public List<Product> findByFilter(ProductsFilter filter, Page page) {
        Criteria criteria = getCriteria(filter);
        if (page != null) {
            criteria.setFirstResult(page.getCurrentStart());
            criteria.setMaxResults(page.getPageSize());
        }
        return (List<Product>) criteria.list();
    }

    @Override
    public int getProductsCount(ProductsFilter filter) {
        Criteria criteria = getCriteria(filter);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    private Criteria getCriteria(ProductsFilter filter) {
        Criteria criteria = currentSession().createCriteria(daoType);
        if (filter != null) {
            if (filter.getNameFilter() != null) {
                criteria.add(Restrictions.ilike("name", filter.getNameFilter().getPattern(), MatchMode.ANYWHERE));
            }
            if (filter.getPriceRange() != null && filter.getPriceRange().getMin() != null) {
                BigDecimal num = BigDecimal.valueOf(filter.getPriceRange().getMin());
                criteria.add(Restrictions.gt("sellingPrice", num));
            }
            if (filter.getPriceRange() != null && filter.getPriceRange().getMax() != null) {
                BigDecimal num = BigDecimal.valueOf(filter.getPriceRange().getMax());
                criteria.add(Restrictions.lt("sellingPrice", num));
            }
            if (filter.getColor() != null) {
            	criteria.createAlias("productItems", "productItem");
                criteria.add(Restrictions.eq("productItem.color", filter.getColor()));
            }
            if (filter.getCategoryId() != null) {
                criteria.add(Restrictions.eq("category.id", filter.getCategoryId()));
            }
            if (filter.getSort() != null) {
            	if(filter.getSort().getSortBy() != null) {
            		if(filter.getSort().getSortBy().equals("price")) {
            			filter.getSort().setSortBy("basePrice");
            		}
            		if(filter.getSort().getSortOrder() == Sort.SortOrder.ASC) {
            			criteria.addOrder(Order.asc(filter.getSort().getSortBy()));
            		}
            		else {
            			criteria.addOrder(Order.desc(filter.getSort().getSortBy()));
            		}
            	}
            }
        }
        return criteria;
    }
}
