package com.jets.ecommerce.service.beans;


public class ProductItemBean {

    private Integer id;
    private String color;
    private int quantityInStock;
    private ProductBean product;

    public ProductItemBean() {
    }

    public ProductItemBean(Integer id) {
        this.id = id;
    }

    public ProductItemBean(String color, int quantityInStock, ProductBean product) {
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

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

}
