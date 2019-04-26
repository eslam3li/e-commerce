package com.jets.ecommerce.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.google.gson.Gson;
import com.jets.ecommerce.filter.DataFilter;


public class JsonAdapter {
	
	private Gson gson;
	
	public JsonAdapter() {
		gson = new Gson();
	}
	
	public String toJson(List list, Class arrayClass, DataFilter dataFilter) {
		if(list.isEmpty()) {
			return "[]";
		}
		else {
			if(dataFilter != null) {
				list = dataFilter.filterList(list);
			}
			return gson.toJson(list.toArray(), arrayClass);
		}
	}
	
	public String toJson(Set set, Class arrayClass, DataFilter dataFilter) {
		List list = new ArrayList(set.size());
		list.addAll(set);
		return toJson(list, arrayClass, dataFilter);
	}
	
	public String toJson(Object object, Class objectClass, DataFilter dataFilter) {
		if(object == null) {
			return "null";
		}
		else {
			if(dataFilter != null) {
				object = dataFilter.filter(object);
			}
			return gson.toJson(object, objectClass);
		}
	}
	
	public <T> T fromJson(String json, Class<T> objectClass) {
		if(json != null) {
			return gson.fromJson(json, objectClass);
		}
		else {
			return null;
		}
	}
	
	public String bundleJson(String[] keys, String... values) {
		if(keys.length == values.length) {
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
			for(int i = 0 ; i < keys.length ; i++) {
				jsonObjectBuilder.add(keys[i], values[i]);
			}
			return jsonObjectBuilder.build().toString();
		}
		else {
			throw new RuntimeException("keys and values must be of the same length");
		}
	}

}
