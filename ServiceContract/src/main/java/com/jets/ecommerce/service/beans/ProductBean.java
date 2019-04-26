package com.jets.ecommerce.service.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class ProductBean {

    private Integer id;
    private String name;
    private String description;
    private String displayPicture;
    private final Set<String> pictures = new HashSet<>();
    private BigDecimal basePrice;
    private BigDecimal sellingPrice;
    private BigDecimal sale;
    private CategoryBean category;
    private final Set<ProductItemBean> productItems = new HashSet<>();

    public ProductBean() {
    }

    public ProductBean(Integer id) {
        this.id = id;
    }

    public ProductBean(String name, String description, String displayPicture, BigDecimal basePrice, BigDecimal sellingPrice, CategoryBean category) {
        this.name = name;
        this.description = description;
        this.displayPicture = displayPicture;
        this.basePrice = basePrice;
        this.sellingPrice = sellingPrice;
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

    public Set<String> getPictures() {
        return pictures;
    }

    public void addPicture(String picture) {
        this.pictures.add(picture);
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }
    
    public Set<ProductItemBean> getProductItems() {
        return productItems;
    }

    public void addProductItem(ProductItemBean productItem) {
        this.productItems.add(productItem);
    }

}
