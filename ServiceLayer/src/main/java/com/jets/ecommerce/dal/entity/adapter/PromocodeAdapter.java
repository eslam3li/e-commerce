package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Promocode;
import com.jets.ecommerce.service.beans.PromocodeBean;


public class PromocodeAdapter {

    public static Promocode fromBean(PromocodeBean bean) {
        if (bean == null) {
            return null;
        }
        Promocode promocode = new Promocode(bean.getCode(), bean.getDiscount());
        return promocode;
    }

    public static PromocodeBean toBean(Promocode entity) {
        if (entity == null) {
            return null;
        }
        PromocodeBean promocodeBean = new PromocodeBean(entity.getId());
        promocodeBean.setCode(entity.getCode());
        promocodeBean.setDiscount(entity.getDiscount());
        return promocodeBean;
    }
}
