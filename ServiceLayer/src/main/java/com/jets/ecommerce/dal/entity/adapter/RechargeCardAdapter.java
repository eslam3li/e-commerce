package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.RechargeCard;
import com.jets.ecommerce.service.beans.RechargeCardBean;


public class RechargeCardAdapter {

    public static RechargeCard fromBean(RechargeCardBean bean) {
        if (bean == null) {
            return null;
        }
        RechargeCard rechargeCard = new RechargeCard(bean.getCode(), bean.getAmmount());
        return rechargeCard;
    }

    public static RechargeCardBean toBean(RechargeCard entity) {
        if (entity == null) {
            return null;
        }
        RechargeCardBean rechargeCardBean = new RechargeCardBean(entity.getId());
        rechargeCardBean.setCode(entity.getCode());
        rechargeCardBean.setAmmount(entity.getAmmount());
        return rechargeCardBean;
    }

}
