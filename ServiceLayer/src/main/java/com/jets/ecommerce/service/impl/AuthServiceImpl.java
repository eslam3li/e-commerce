package com.jets.ecommerce.service.impl;

import com.jets.ecommerce.dal.dao.AdminsDao;
import com.jets.ecommerce.dal.dao.DaosFactory;
import com.jets.ecommerce.dal.dao.Page;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.entity.Admin;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.dal.entity.adapter.AdminAdapter;
import com.jets.ecommerce.dal.entity.adapter.UserAdapter;
import com.jets.ecommerce.service.AuthService;
import com.jets.ecommerce.service.beans.AdminBean;
import com.jets.ecommerce.service.beans.UserBean;
import com.jets.ecommerce.service.beans.filters.UsersFilter;
import com.jets.ecommerce.service.exceptions.LoginException;
import com.jets.ecommerce.service.exceptions.RegisterationException;
import com.jets.ecommerce.service.security.SecurityContext;

import java.math.BigDecimal;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class AuthServiceImpl implements AuthService {

    private final UsersDao usersDao;
    private final AdminsDao adminsDao;

    public AuthServiceImpl(DaosFactory daosFactory) {
        this.usersDao = daosFactory.getUsersDao();
        this.adminsDao = daosFactory.getAdminsDao();
    }

    @Override
    public AdminBean loginAdmin(String email, String password) throws LoginException {
        Admin admin = adminsDao.findByEmailAndPassword(email, password);
        if (admin == null) {
            throw new LoginException("Email or password is wrong.");
        }
        AdminBean adminBean = AdminAdapter.toBean(admin);
        adminBean.setPassword(null);
        SecurityContext.setUser(new com.jets.ecommerce.service.security.Admin(admin.getId()));
        return adminBean;
    }

    @Override
    public UserBean loginUser(String email, String password) throws LoginException {
        User user = usersDao.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new LoginException("Email or password is wrong.");
        }
        UserBean userBean = UserAdapter.toBean(user);
        userBean.setPassword(null);
        SecurityContext.setUser(new com.jets.ecommerce.service.security.Client(user.getId()));
        return userBean;
    }

    @Override
    public void logout() {
        SecurityContext.clear();
    }

    @Override
    public void addAdmin(AdminBean adminBean) throws RegisterationException {
        Admin findByEmail = adminsDao.findByEmail(adminBean.getEmail());
        if (findByEmail != null) {
            throw new RegisterationException("This email already exists");
        }
        Admin admin = AdminAdapter.fromBean(adminBean);
        adminsDao.save(admin);
    }

    @Override
    public void addUser(UserBean userBean) throws RegisterationException {
        User findByEmail = usersDao.findByEmail(userBean.getEmail());
        if (findByEmail != null) {
            throw new RegisterationException("This email already exists");
        }
        User user = UserAdapter.fromBean(userBean);
        user.setBalance(BigDecimal.ZERO);
        usersDao.save(user);
    }

    @Override
    public List<UserBean> filterUsers(UsersFilter filter, int start, int pageSize) {
        return usersDao.findByFilter(filter, new Page(start, pageSize))
                .stream().map(UserAdapter::toBean)
                .collect(toList());
    }

    @Override
    public int getUsersCount(UsersFilter filter) {
        return usersDao.getUsersCount(filter);
    }

    @Override
    public List<AdminBean> getAllAdmins() {
        return adminsDao.findAll().stream()
                .map(AdminAdapter::toBean)
                .collect(toList());
    }

	@Override
	public boolean checkBalance(BigDecimal balance) {
		User user = usersDao.get(SecurityContext.getCurrentUserId());
		return user.getBalance().compareTo(balance) > 0;
	}

}
