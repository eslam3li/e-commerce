
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity(name = "product_items")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
    "color", "product_id"
}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductItem implements Serializable, com.jets.ecommerce.dal.dao.Entity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int quantityInStock;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public ProductItem() {
    }

    public ProductItem(String color, int quantityInStock, Product product) {
        this.color = color;
        this.quantityInStock = quantityInStock;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
