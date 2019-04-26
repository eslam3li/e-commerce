
package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.PromocodesDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.Promocode;
import com.jets.ecommerce.dal.entity.adapter.PromocodeAdapter;
import com.jets.ecommerce.service.PromocodesService;
import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.service.exceptions.CodeAlreadyUsedException;
import com.jets.ecommerce.service.security.SecurityContext;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


public class PromocodesServiceImpl implements PromocodesService {

    private final PromocodesDao promocodesDao;
    private final UsersDao usersDao;

    public PromocodesServiceImpl(DaosFactory daosFactory) {
        this.promocodesDao = daosFactory.getPromocodesDao();
        this.usersDao = daosFactory.getUsersDao();
    }

    @Override
    public List<PromocodeBean> getAllPromocodes(int start, int pageSize) {
        return promocodesDao.findAll(new Page(start, pageSize)).stream()
                .map(PromocodeAdapter::toBean)
                .collect(toList());
    }
    
    @Override
	public PromocodeBean findByCode(String code) throws CodeAlreadyUsedException {
    	Set<Promocode> usedPromocodes = usersDao.get(SecurityContext.getCurrentUserId()).getUsedPromocodes();
    	Promocode promocode = promocodesDao.findByCode(code);
    	if(usedPromocodes.contains(promocode)) {
    		throw new CodeAlreadyUsedException("This coupon is already used");
    	}
		return PromocodeAdapter.toBean(promocode);
	}

	@Override
    public void addPromocode(PromocodeBean promocodeBean) {
        Promocode entity = PromocodeAdapter.fromBean(promocodeBean);
        promocodesDao.save(entity);
    }

    @Override
    public void removePromocode(PromocodeBean promocodeBean) {
        Promocode promocode = promocodesDao.load(promocodeBean.getId());
        promocodesDao.delete(promocode);
    }

}
