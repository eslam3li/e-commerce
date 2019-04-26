
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "order_items")
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
//    "order_id", "item_id"
//}))
public class OrderItem implements Serializable, com.jets.ecommerce.dal.dao.Entity<OrderItemId> {

    @EmbeddedId
    private OrderItemId id;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("itemId")
    private ProductItem item;

    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Order order, ProductItem item) {
        this.item = item;
        this.order = order;
        updateId();
    }

    private void updateId() {
        if (order != null && item != null) {
            this.id = new OrderItemId(order.getId(), item.getId());
        } else {
            this.id = null;
        }
    }
    
    @Override
    public void setId(OrderItemId id) {
    	this.id = id;
    }

    public OrderItemId getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
