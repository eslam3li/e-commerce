package com.jets.ecommerce.filter;

import com.jets.ecommerce.service.beans.ProductBean;


public class ProductDataFilter implements DataFilter<ProductBean> {
	
	private static final String DEFAULT_PICTURE = "images/bg-03.jpg";

	@Override
	public ProductBean filter(ProductBean bean) {
		if(bean.getDisplayPicture() == null) {
			bean.setDisplayPicture(DEFAULT_PICTURE);
		}
		if(bean.getPictures().size() == 0) {
			bean.addPicture(bean.getDisplayPicture());
		}
		return bean;
	}

}
