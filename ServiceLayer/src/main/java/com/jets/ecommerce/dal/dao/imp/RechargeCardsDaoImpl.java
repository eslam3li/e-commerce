package com.jets.ecommerce.dal.dao.imp;

import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.RechargeCardsDao;
import com.jets.ecommerce.dal.entity.RechargeCard;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class RechargeCardsDaoImpl extends GenericDaoImpl<RechargeCard, Integer> implements RechargeCardsDao {

    public RechargeCardsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public RechargeCard findByCode(String code) {
        return (RechargeCard) currentSession().createCriteria(daoType)
                .add(Restrictions.eq("code", code)).uniqueResult();
    }

    @Override
    public List<RechargeCard> findUnused() {
        Criteria criteria = currentSession().createCriteria(daoType)
                .add(Restrictions.isNull("user"));
        return (List<RechargeCard>) criteria.list();
    }

}
