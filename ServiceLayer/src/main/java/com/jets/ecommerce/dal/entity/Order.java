package com.jets.ecommerce.dal.entity;

import com.jets.ecommerce.service.beans.OrderState;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "orders")
public class Order implements Serializable, com.jets.ecommerce.dal.dao.Entity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", nullable = false)
    private OrderState state;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    private Promocode promoCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>(0);

    public Order() {
    }

    public Order(Date purchaseDate, OrderState state, User user) {
        this.purchaseDate = purchaseDate;
        this.state = state;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Promocode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Promocode promoCode) {
        this.promoCode = promoCode;
    }

    void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItem.getOrder() != null) {
            orderItem.getOrder().getOrderItems().remove(orderItem);
        }
        orderItem.setOrder(this);
        this.getOrderItems().add(orderItem);
    }

}
