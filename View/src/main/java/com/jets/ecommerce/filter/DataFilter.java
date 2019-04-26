package com.jets.ecommerce.filter;

import java.util.List;


public interface DataFilter<T> {
	
	T filter(T bean);
	
	default List<T> filterList(List<T> list) {
		for(int i = 0 ; i < list.size() ; i++) {
			list.set(i, filter(list.get(i)));
		}
		return list;
	}
	
}
