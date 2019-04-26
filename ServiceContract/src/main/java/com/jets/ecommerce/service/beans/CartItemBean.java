package com.jets.ecommerce.service.beans;


public class CartItemBean {

	private String id;
    private ProductItemBean productItemBean;
    private int quantity;

    public CartItemBean() {
    }
    
    public CartItemBean(int userId, int itemId) {
    	this.id = ""+userId+itemId;
    }

    public CartItemBean(ProductItemBean productItemBean, int quantity) {
    	this.productItemBean = productItemBean;
        this.quantity = quantity;
    }
    
    public String getId() {
		return id;
	}

    public ProductItemBean getProductItemBean() {
        return productItemBean;
    }

    public void setProductItemBean(ProductItemBean productItemBean) {
        this.productItemBean = productItemBean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productItemBean == null) ? 0 : productItemBean.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItemBean other = (CartItemBean) obj;
		if (productItemBean == null) {
			if (other.productItemBean != null)
				return false;
		} else if (!productItemBean.getId().equals(other.productItemBean.getId()))
			return false;
		return true;
	}

}
