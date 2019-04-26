
package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.RechargeCardsDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.RechargeCard;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.dal.entity.adapter.RechargeCardAdapter;
import com.jets.ecommerce.service.RechargeCardsService;
import com.jets.ecommerce.service.beans.RechargeCardBean;
import com.jets.ecommerce.service.security.SecurityContext;

import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;


public class RechargeCardsServiceImpl implements RechargeCardsService {

    private final RechargeCardsDao rechargeCardsDao;
    private final UsersDao usersDao;

    public RechargeCardsServiceImpl(DaosFactory daosFactory) {
        this.rechargeCardsDao = daosFactory.getRechargeCardsDao();
        this.usersDao = daosFactory.getUsersDao();
    }

    @Override
    public List<RechargeCardBean> getAllUnusedCards() {
        List<RechargeCard> rechargeCards = rechargeCardsDao.findUnused();
        return rechargeCards.stream()
                .map(RechargeCardAdapter::toBean)
                .collect(toList());
    }

    @Override
    public void addRechargeCards(int number, int amount) {
        for (int i = 0; i < number; i++) {
            String code = UUID.randomUUID().toString();
            RechargeCard rechargeCard = new RechargeCard(code, amount);
            rechargeCardsDao.save(rechargeCard);
        }
    }

    @Override
    public void useRechargeCard(String code) {
        User user = usersDao.load(SecurityContext.getCurrentUserId());
        RechargeCard rechargeCard = rechargeCardsDao.findByCode(code);
        rechargeCard.setUser(user);
        rechargeCardsDao.update(rechargeCard);
    }

}
