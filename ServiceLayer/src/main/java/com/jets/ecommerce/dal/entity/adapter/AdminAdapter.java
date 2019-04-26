
package com.jets.ecommerce.dal.entity.adapter;

import com.jets.ecommerce.dal.entity.Admin;
import com.jets.ecommerce.service.beans.AdminBean;


public class AdminAdapter {

    public static Admin fromBean(AdminBean bean) {
        if (bean == null) {
            return null;
        }
        Admin admin = new Admin(bean.getName(), bean.getEmail(), bean.getPassword());
        admin.setPhone(bean.getPhone());
        return admin;
    }

    public static AdminBean toBean(Admin entity) {
        if (entity == null) {
            return null;
        }
        AdminBean adminBean = new AdminBean(entity.getId());
        adminBean.setName(entity.getName());
        adminBean.setEmail(entity.getEmail());
        adminBean.setPhone(entity.getPhone());
        return adminBean;
    }
}
