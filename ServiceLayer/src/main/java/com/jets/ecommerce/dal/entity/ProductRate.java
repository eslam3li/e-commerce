
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity(name = "product_rates")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
    "user_id", "product_id"
}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductRate implements Serializable, com.jets.ecommerce.dal.dao.Entity<ProductRateId> {

    @EmbeddedId
    private ProductRateId id;

    @Column(nullable = false)
    private int rate;

    @Column(nullable = true)
    private String comment;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    public ProductRate() {
    }

    public ProductRate(int rate, User user, Product product) {
        this.rate = rate;
        this.user = user;
        this.product = product;
        updateId();
    }

    private void updateId() {
        if (user != null && product != null) {
            this.id = new ProductRateId(user.getId(), product.getId());
        } else {
            this.id = null;
        }
    }

    @Override
    public void setId(ProductRateId id) {
        this.id = id;
    }

    public ProductRateId getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        updateId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        updateId();
    }

}
