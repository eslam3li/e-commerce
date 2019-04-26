package com.jets.ecommerce.service.beans;


public class CategoryBean {

    private Integer id;
    private String name;
    private String picture;
    private CategoryBean parentCategory;

    public CategoryBean() {
    }

    public CategoryBean(Integer id) {
        this.id = id;
    }

    public CategoryBean(String name, CategoryBean parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public CategoryBean getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryBean parentCategory) {
        this.parentCategory = parentCategory;
    }

}
