
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "cart_Items")
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
//    "user_id", "item_id"
//}))
public class CartItem implements Serializable, com.jets.ecommerce.dal.dao.Entity<CartItemId> {

    @EmbeddedId
    private CartItemId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("itemId")
    private ProductItem item;

    private int quantity;

    public CartItem() {
    }

    public CartItem(User user, ProductItem item, int quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
        updateId();
    }

    private void updateId() {
        if (user != null && item != null) {
            this.id = new CartItemId(user.getId(), item.getId());
        } else {
            this.id = null;
        }
    }
    
    @Override
    public void setId(CartItemId id) {
    	this.id = id;
    }

    public CartItemId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        updateId();
    }

    public ProductItem getItem() {
        return item;
    }

    public void setItem(ProductItem item) {
        this.item = item;
        updateId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
