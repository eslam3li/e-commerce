
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


@Embeddable
public class CartItemId implements Serializable {

    private Integer userId;

    private Integer itemId;

    public CartItemId() {
    }

    public CartItemId(Integer userId, Integer itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.userId);
        hash = 67 * hash + Objects.hashCode(this.itemId);
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
        final CartItemId other = (CartItemId) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        return true;
    }

}
