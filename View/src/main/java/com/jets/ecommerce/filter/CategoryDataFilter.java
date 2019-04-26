package com.jets.ecommerce.filter;

import com.jets.ecommerce.service.beans.CategoryBean;


public class CategoryDataFilter implements DataFilter<CategoryBean> {
	
	private static final String DEFAULT_PICTURE = "images/bg-03.jpg";

	@Override
	public CategoryBean filter(CategoryBean bean) {
		if(bean.getPicture() == null) {
			bean.setPicture(DEFAULT_PICTURE);
		}
		return bean;
	}

}
