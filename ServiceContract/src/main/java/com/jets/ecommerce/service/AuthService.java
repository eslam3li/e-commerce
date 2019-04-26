/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service;

import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.beans.filters.UsersFilter;
import com.jets.ecommerce.service.exceptions.LoginException;
import com.jets.ecommerce.service.exceptions.RegisterationException;
import com.jets.ecommerce.service.security.Admin;
import com.jets.ecommerce.service.security.Client;
import com.jets.ecommerce.service.security.annotations.Access;

import java.math.BigDecimal;
import java.util.List;


public interface AuthService {

    /**
     * The password returned is null.
     *
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    AdminBean loginAdmin(String email, String password) throws LoginException;

    /**
     * The password returned is null.
     *
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    UserBean loginUser(String email, String password) throws LoginException;

    void logout();

    @Access(Admin.class)
    void addAdmin(AdminBean adminBean) throws RegisterationException;

    /**
     * used to register users
     *
     * @param userBean
     * @throws com.jets.ecommerce.service.exceptions.RegisterationException
     */
    void addUser(UserBean userBean) throws RegisterationException;

    /**
     * use to paginate throw the list. The password returned is null.
     *
     * @param filter
     * @param start the index of the first item in the page
     * @param pageSize
     * @return the returned list has a length of all users matching the search
     * result don't try to access any elements outside the range start to
     * start+pagesize-1
     */
    @Access(Admin.class)
    List<UserBean> filterUsers(UsersFilter filter, int start, int pageSize);

    @Access(Admin.class)
    int getUsersCount(UsersFilter filter);

    @Access(Admin.class)
    List<AdminBean> getAllAdmins();
    
    @Access(Client.class)
    boolean checkBalance(BigDecimal balance);

}
