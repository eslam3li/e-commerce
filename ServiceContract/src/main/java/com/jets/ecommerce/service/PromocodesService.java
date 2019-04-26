/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.PromocodeBean;
import com.jets.ecommerce.service.exceptions.CodeAlreadyUsedException;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.annotations.Access;
import java.util.List;


public interface PromocodesService {

    /**
     * use to paginate throw the list of promocodes
     *
     * @param start the index of the first item in the page
     * @param pageSize
     * @return the returned list has a length of all users matching the search
     * result don't try to access any elements outside the range start to
     * start+pagesize-1
     */
    @Access(Admin.class)
    List<PromocodeBean> getAllPromocodes(int start, int pageSize);
    
    @Access(Client.class)
    PromocodeBean findByCode(String code) throws CodeAlreadyUsedException;

    @Access(Admin.class)
    void addPromocode(PromocodeBean promocodeBean);

    @Access(Admin.class)
    void removePromocode(PromocodeBean promocodeBean);

}
