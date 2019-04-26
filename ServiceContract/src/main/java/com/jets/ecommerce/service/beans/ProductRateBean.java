package com.jets.ecommerce.service.beans;


public class ProductRateBean {

	private String id;
    private UserBean user;
    private ProductBean product;
    private String comment;
    private int rate;

    public ProductRateBean() {
    }
    
    public ProductRateBean(int userId, int productId) {
    	this.id = ""+userId+productId;
    }

    public ProductRateBean(UserBean user, ProductBean product, int rate) {
        this.user = user;
        this.product = product;
        this.rate = rate;
    }
    public ProductRateBean(ProductBean product,int rate,String comment){
        this.product=product;
         this.rate = rate;
          this.comment=comment;
    }
    
    public String getId() {
		return id;
	}

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

}
