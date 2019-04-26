package com.jets.ecommerce.service.beans.filters;

public class Sort {
	
	String sortBy;
	SortOrder sortOrder;
	
	public Sort() {
	}
	
	public Sort(String sortBy, SortOrder sortOrder) {
		super();
		this.sortBy = sortBy;
		this.sortOrder = sortOrder;
	}
	
	public Sort(String sortBy, String sortOrder) {
		super();
		this.sortBy = sortBy;
		setSortOrder(sortOrder);
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}
	
	public void setSortOrder(String sortOrder) {
		if(sortOrder != null) {
			for(SortOrder order : SortOrder.values()) {
				if(sortOrder.equalsIgnoreCase(order.toString())) {
					this.sortOrder = order;
				}
			}
		}
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public static enum SortOrder {
		ASC, DESC
	}

}
