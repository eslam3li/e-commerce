package com.jets.ecommerce.service.beans;


public class OrderItemBean {

    private String id;
    private ProductItemBean productItem;
    private int quantity;

    public OrderItemBean() {
    }

    public OrderItemBean(int orderId, int itemId) {
        this.id = "" + orderId + itemId;
    }

    public OrderItemBean(ProductItemBean productItem, int quantity) {
        this.productItem = productItem;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public ProductItemBean getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItemBean productItem) {
        this.productItem = productItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
