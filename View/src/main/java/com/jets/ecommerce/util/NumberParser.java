package com.jets.ecommerce.util;


public class NumberParser {

	public static int parseInt(String integer, int defaultValue) {
		try {
			if(integer != null) {
				return Integer.parseInt(integer);
			}
		}
		catch(NumberFormatException e) {
			return defaultValue;
		}
		return defaultValue;
	}
	
}
