
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


@Embeddable
public class OrderItemId implements Serializable {

    private Integer orderId;

    private Integer itemId;

    public OrderItemId() {
    }

    public OrderItemId(Integer orderId, Integer itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.orderId);
        hash = 79 * hash + Objects.hashCode(this.itemId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItemId other = (OrderItemId) obj;
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        return true;
    }

}
