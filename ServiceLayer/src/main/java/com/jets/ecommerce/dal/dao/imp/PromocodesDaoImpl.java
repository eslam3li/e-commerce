
package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.PromocodesDao;
import com.jets.ecommerce.dal.entity.Promocode;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class PromocodesDaoImpl extends GenericDaoImpl<Promocode, Integer> implements PromocodesDao {

    public PromocodesDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Promocode findByCode(String code) {
        return (Promocode) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("code", code)).uniqueResult();
    }

    @Override
    public List<Promocode> findAll(Page page) {
        Criteria criteria = currentSession().createCriteria(daoType);
        if (page != null) {
        	if(page.getCurrentStart() > 0) {
        		criteria.setFirstResult(page.getCurrentStart());
        	}
        	if(page.getPageSize() > 0) {
        		criteria.setMaxResults(page.getPageSize());
        	}
        }
        return (List<Promocode>) criteria.list();
    }

}
