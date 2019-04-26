package com.jets.ecommerce.controller.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jets.ecommerce.filter.CategoryDataFilter;
import com.jets.ecommerce.filter.ProductDataFilter;
import com.jets.ecommerce.service.CategoriesService;
import com.jets.ecommerce.service.ProductsService;
import com.jets.ecommerce.service.ServicesProvider;
import com.jets.ecommerce.service.beans.CategoryBean;
import com.jets.ecommerce.service.beans.ProductBean;
import com.jets.ecommerce.service.beans.filters.PatternFilter;
import com.jets.ecommerce.service.beans.filters.ProductsFilter;
import com.jets.ecommerce.service.beans.filters.Range;
import com.jets.ecommerce.service.beans.filters.Sort;
import com.jets.ecommerce.util.JsonAdapter;
import com.jets.ecommerce.util.NumberParser;


public class ProductServletGetter {
	
	private static final int PAGE_SIZE = 20;
	
	private ProductsService productsService;
	private CategoriesService categoriesService;
	private JsonAdapter jsonAdapter;
	private int category;
	private ProductsFilter productsFilter;
	private int page;
	private CategoryBean currentCategory;
	private List<CategoryBean> categoryList;
	private List<ProductBean> productList;
	private Integer productsCount;
	
	public ProductServletGetter(ServicesProvider servicesProvider, String categoryId, String search, String color, String sortBy, String sortOrder, String priceMin, String priceMax, String pageNumber) {
		productsService = servicesProvider.getProductsService();
		categoriesService = servicesProvider.getCategoriesService();
		jsonAdapter = new JsonAdapter();
		productsFilter = new ProductsFilter();
		category = NumberParser.parseInt(categoryId, -1);
		if(category != -1) {
			productsFilter.setCategoryId(category);
		}
		if(search != null) {
			productsFilter.setNameFilter(new PatternFilter(search));
		}
		Range<Integer> priceRange = new Range<Integer>(NumberParser.parseInt(priceMin, 0),
				NumberParser.parseInt(priceMax, Integer.MAX_VALUE));
		if(priceRange.getMin() == -1) {
			priceRange.setMin(0);
		}
		if(priceRange.getMax() == -1) {
			priceRange.setMax(Integer.MAX_VALUE);
		}
		productsFilter.setPriceRange(priceRange);
		productsFilter.setColor(color);
		productsFilter.setSort(new Sort(sortBy, sortOrder));
		page = NumberParser.parseInt(pageNumber, 0);
		if(page != 0) {
			page--;
		}
	}
	
	public ProductServletGetter(ServicesProvider servicesProvider, HttpServletRequest req) {
		this(servicesProvider, req.getParameter("category"), req.getParameter("search"), req.getParameter("color"),
				req.getParameter("sortBy"), req.getParameter("sortOrder"),
				req.getParameter("priceMin"), req.getParameter("priceMax"),
				req.getParameter("page"));
	}
	
	public int getPages() {
		if(productsCount == null) {
			productsCount = productsService.getProductsCount(productsFilter);
		}
		return (int) Math.ceil(1.0 * productsCount / PAGE_SIZE);
	}
	
	public String getCategories() {
		if(categoryList == null) {
			if(category == -1) {
				categoryList = categoriesService.getTopLevelCategories();
			}
			else {
				categoryList = categoriesService.getSubCategories(category);
			}
		}
		return jsonAdapter.toJson(categoryList, CategoryBean[].class, new CategoryDataFilter());
	}
	
	public String getCategory() {
		if(category != -1) {
			currentCategory = categoriesService.getCategory(category);
		}
		return jsonAdapter.toJson(currentCategory, CategoryBean.class, new CategoryDataFilter());
	}
	
	public String getProducts() {
		if(productList == null) {
			productList = productsService.searchProducts(productsFilter, page * PAGE_SIZE, PAGE_SIZE);
		}
		return jsonAdapter.toJson(productList, ProductBean[].class, new ProductDataFilter());
	}
	
	public String getAll() {
		return jsonAdapter.bundleJson(new String[] {"pages", "category", "categories", "products"},
				""+getPages(), getCategory(), getCategories(), getProducts());
	}
	
}
