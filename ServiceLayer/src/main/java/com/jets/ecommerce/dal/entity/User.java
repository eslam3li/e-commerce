
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;


@Entity(name = "users")
public class User implements Serializable, com.jets.ecommerce.dal.dao.Entity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true)
    private String phone;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String job;

    @Column(nullable = true)
    private String picture;

    @Temporal(DATE)
    @Column(nullable = true)
    private Date birthDate;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany
    private Set<Promocode> usedPromocodes = new HashSet<>(0);

    @OneToMany(mappedBy = "user")
    private Set<RechargeCard> usedRechargeCards = new HashSet<>(0);

    @OneToMany
    private Set<Category> interestingCategories = new HashSet<>(0);

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>(0);

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>(0);

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = BigDecimal.ZERO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    public Set<Promocode> getUsedPromocodes() {
        return usedPromocodes;
    }

    void setUsedPromocodes(Set<Promocode> usedPromocodes) {
        this.usedPromocodes = usedPromocodes;
    }

    public void addUsedPromocode(Promocode promocode) {
        this.getUsedPromocodes().add(promocode);
    }

    public Set<RechargeCard> getUsedRechargeCards() {
        return usedRechargeCards;
    }

    void setUsedRechargeCards(Set<RechargeCard> usedRechargeCards) {
        this.usedRechargeCards = usedRechargeCards;
    }

    void addUsedRechargeCar(RechargeCard rechargeCard) {
        if (rechargeCard.getUser() != null) {
            rechargeCard.getUser().getUsedRechargeCards().remove(rechargeCard);
        }
        rechargeCard.setUser(this);
        this.getUsedRechargeCards().add(rechargeCard);
    }

    public Set<Category> getInterestingCategories() {
        return interestingCategories;
    }

    void setInterestingCategories(Set<Category> interestingCategories) {
        this.interestingCategories = interestingCategories;
    }

    public void addInterestingCategory(Category category) {
        this.getInterestingCategories().add(category);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (order.getUser() != null) {
            order.getUser().getOrders().remove(order);
        }
        order.setUser(this);
        this.getOrders().add(order);
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        if (cartItem.getUser() != null) {
            cartItem.getUser().getCartItems().remove(cartItem);
        }
        cartItem.setUser(this);
        this.getCartItems().add(cartItem);
    }

}
