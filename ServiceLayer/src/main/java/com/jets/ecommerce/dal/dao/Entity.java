package com.jets.ecommerce.dal.dao;

public interface Entity<K> {
	
	void setId(K id);
	
	K getId();

}
