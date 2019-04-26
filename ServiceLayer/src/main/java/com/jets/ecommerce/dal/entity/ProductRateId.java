
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


@Embeddable
public class ProductRateId implements Serializable {

    private Integer userId;

    private Integer productId;

    public ProductRateId() {
    }

    public ProductRateId(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.productId);
        hash = 79 * hash + Objects.hashCode(this.userId);
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
        final ProductRateId other = (ProductRateId) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

}
