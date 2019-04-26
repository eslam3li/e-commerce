package com.jets.ecommerce.dal.cfg;

import com.ibrahim.hibernate.transaction.TransactionManager;
import com.jets.ecommerce.dal.dao.AdminsDao;
import com.jets.ecommerce.dal.dao.CategoriesDao;
import com.jets.ecommerce.dal.dao.OrdersDao;
import com.jets.ecommerce.dal.dao.ProductItemsDao;
import com.jets.ecommerce.dal.dao.ProductsDao;
import com.jets.ecommerce.dal.dao.PromocodesDao;
import com.jets.ecommerce.dal.dao.UsersDao;
import com.jets.ecommerce.dal.dao.imp.AdminsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.CategoriesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.OrdersDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductItemsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.ProductsDaoImpl;
import com.jets.ecommerce.dal.dao.imp.PromocodesDaoImpl;
import com.jets.ecommerce.dal.dao.imp.UsersDaoImpl;
import com.jets.ecommerce.dal.entity.Admin;
import com.jets.ecommerce.dal.entity.Category;
import com.jets.ecommerce.dal.entity.Order;
import com.jets.ecommerce.dal.entity.OrderItem;
import com.jets.ecommerce.dal.entity.Product;
import com.jets.ecommerce.dal.entity.ProductItem;
import com.jets.ecommerce.dal.entity.Promocode;
import com.jets.ecommerce.dal.entity.User;
import com.jets.ecommerce.service.beans.OrderState;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;


public class DummyData {

    private final TransactionManager transactionManager;
    private final AdminsDao adminsDao;
    private final CategoriesDao categoriesDao;
    private final ProductsDao productsDao;
    private final ProductItemsDao productItemsDao;
    private final PromocodesDao promocodesDao;
    private final UsersDao usersDao;
    private final OrdersDao ordersDao;

    private DummyData(SessionFactory sessionFactory) {
        transactionManager = new TransactionManager(sessionFactory);

        adminsDao = new AdminsDaoImpl(sessionFactory);
        categoriesDao = new CategoriesDaoImpl(sessionFactory);
        productsDao = new ProductsDaoImpl(sessionFactory);
        productItemsDao = new ProductItemsDaoImpl(sessionFactory);
        promocodesDao = new PromocodesDaoImpl(sessionFactory);
        usersDao = new UsersDaoImpl(sessionFactory);
        ordersDao = new OrdersDaoImpl(sessionFactory);
    }

    public static DummyData init(SessionFactory sessionFactory) {
        return new DummyData(sessionFactory);
    }

    public DummyData insertAll() throws Exception {
        insertAdmins();
        insertUsers();
        insertProducts();
        insertPromocodes();
        return this;
    }

    public DummyData insertUsers() throws Exception {
        transactionManager.runInTransaction(() -> {
            usersDao.save(new User("Ibrahim Yousre", "ib.yousre@gmail.com", "hima"));
            for (int i = 0; i < 100; i++) {
                User user = new User("user" + i, "user" + i + "@gmail.com", "user");
                usersDao.save(user);
            }
            return null;
        });
        return this;
    }

    public DummyData insertAdmins() throws Exception {
        transactionManager.runInTransaction(() -> {
            Admin admin = new Admin("admin", "admin@admin.com", "admin");
            adminsDao.save(admin);
            return null;
        });
        return this;
    }

    public DummyData insertPromocodes() throws Exception {
        transactionManager.runInTransaction(() -> {
            Promocode promocode = new Promocode("HITHERE", new BigDecimal(10));
            promocodesDao.save(promocode);
            return null;
        });
        return this;
    }

    public DummyData insertProducts() throws Exception {
        transactionManager.runInTransaction(() -> {
            List<Category> categories = Arrays.asList(new Category[]{
                new Category("Men"),
                new Category("Women"),
                new Category("Kids"),
                new Category("Men 1"),
                new Category("Men 2")
            });
            categories.forEach((category) -> {
                categoriesDao.save(category);
            });

            categories.get(3).setParentCategory(categories.get(0));
            categories.get(4).setParentCategory(categories.get(0));

            categoriesDao.save(categories.get(3));
            categoriesDao.save(categories.get(4));

            categories.forEach((category) -> {
                DummyData.this.insertProductsInCategory(category);
            });

            return null;
        });
        return this;
    }

    private DummyData insertProductsInCategory(Category category) {
        List<Product> products = Arrays.asList(new Product[]{
            new Product("Product 1", "nice product", "picture", BigDecimal.valueOf(200),
            BigDecimal.valueOf(300), category),
            new Product("Product 2", "nice product", "picture", BigDecimal.valueOf(200),
            BigDecimal.valueOf(300), category),
            new Product("Product 3", "nice product", "picture", BigDecimal.valueOf(200),
            BigDecimal.valueOf(300), category),
            new Product("Product 4", "nice product", "picture", BigDecimal.valueOf(200),
            BigDecimal.valueOf(300), category)
        });

        products.forEach((product) -> {
            productsDao.save(product);
            ProductItem item1 = new ProductItem("Red", 15, product);
            ProductItem item2 = new ProductItem("Blue", 15, product);
            productItemsDao.save(item1);
            productItemsDao.save(item2);
        });
        return this;
    }

    public DummyData insertOrders() throws Exception {
        transactionManager.runInTransaction(() -> {
            usersDao.findAll().stream().limit(5).forEach(user -> {
                Order order = new Order(new Date(), OrderState.CONFIRMED, user);
                productItemsDao.findAll().stream().limit(5).forEach((product) -> {
                    OrderItem orderItem = new OrderItem(order, product);
                    orderItem.setQuantity(10);
                    order.addOrderItem(orderItem);
                });
                ordersDao.save(order);
            });
            return null;
        });
        return this;
    }

}
