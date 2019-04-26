/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.dal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ibrahim
 */
@Entity(name = "products")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
    "name", "category_id"
}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable, com.jets.ecommerce.dal.dao.Entity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String displayPicture;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private BigDecimal sellingPrice;

    @Column(nullable = true)
    private BigDecimal sale;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductItem> productItems = new HashSet<>(0);

    @OneToMany(mappedBy = "product")
    private Set<ProductRate> productRates = new HashSet<>(0);

    @ElementCollection
    @Column(nullable = false)
    private Set<String> pictures = new HashSet<>(0);

    public Product() {
    }

    public Product(String name, String description, String displayPicture, BigDecimal basePrice, BigDecimal sellingPrice, Category category) {
        this.name = name;
        this.description = description;
        this.displayPicture = displayPicture;
        setBasePrice(basePrice);
        setSellingPrice(sellingPrice);
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale.setScale(2, RoundingMode.HALF_UP);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ProductItem> getProductItems() {
        return productItems;
    }

    void setProductItems(Set<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public void addProductItem(ProductItem productItem) {
        if (productItem.getProduct() != null) {
            productItem.getProduct().getProductItems().remove(productItem);
        }
        productItem.setProduct(this);
        this.getProductItems().add(productItem);
    }

    public Set<ProductRate> getProductRates() {
        return productRates;
    }

    void setProductRates(Set<ProductRate> productRates) {
        this.productRates = productRates;
    }

    public void addProductRate(ProductRate productRate) {
        if (productRate.getProduct() != null) {
            productRate.getProduct().getProductRates().remove(productRate);
        }
        productRate.setProduct(this);
        this.getProductRates().add(productRate);
    }

    public Set<String> getPictures() {
        return pictures;
    }

    void setPictures(Set<String> pictures) {
        this.pictures = pictures;
    }

    public void addPicture(String picture) {
        this.getPictures().add(picture);
    }

}
