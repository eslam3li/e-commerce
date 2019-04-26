package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.UserBean;

public class UserAdapter {

    public static User fromBean(UserBean bean) {
        if (bean == null) {
            return null;
        }
        User user = new User(bean.getName(), bean.getEmail(), bean.getPassword());
        user.setAddress(bean.getAddress());
        user.setBalance(bean.getBalance());
        user.setBirthDate(bean.getBirthDate());
        user.setJob(bean.getJob());
        user.setPhone(bean.getPhone());
        user.setPicture(bean.getPicture());
        return user;
    }

    public static UserBean toBean(User entity) {
        if (entity == null) {
            return null;
        }
        UserBean userBean = new UserBean(entity.getId());
        userBean.setName(entity.getName());
        userBean.setEmail(entity.getEmail());
        userBean.setAddress(entity.getAddress());
        userBean.setBalance(entity.getBalance());
        userBean.setBirthDate(entity.getBirthDate());
        userBean.setJob(entity.getJob());
        userBean.setPhone(entity.getPhone());
        userBean.setPicture(entity.getPicture());
        return userBean;
    }
}
