/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.beans.filters;


public class ProductsFilter {

    PatternFilter nameFilter;
    Range<Integer> priceRange;
    String color;
    Integer categoryId;
    Sort sort;

    public ProductsFilter() {
    }

    public ProductsFilter(PatternFilter nameFilter, Range<Integer> priceRange, String color, Integer categoryId, Sort sort) {
        this.nameFilter = nameFilter;
        this.priceRange = priceRange;
        this.color = color;
        this.categoryId = categoryId;
        this.sort = sort;
    }

    public PatternFilter getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(PatternFilter nameFilter) {
        this.nameFilter = nameFilter;
    }

    public Range<Integer> getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Range<Integer> priceRange) {
        this.priceRange = priceRange;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

}
