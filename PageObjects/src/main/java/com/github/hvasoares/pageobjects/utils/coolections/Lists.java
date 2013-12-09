package com.github.hvasoares.pageobjects.utils.coolections;

import java.util.ArrayList;
import java.util.List;

public final class Lists {
	public static <T> List<T> revert(List<T> from) {
		ArrayList<T> result = new ArrayList<>();
		for(int i = from.size()-1; i>=0; i--)
			result.add(from.get(i));
		return result;
	}

	public static <T> List<T> concat(T firstValues, List<T> tailValue) {
		ArrayList<T> result = new ArrayList<>();
		result.add(firstValues);
		result.addAll(tailValue);
		return result;
	}

	public static  int multiply(List<Integer> numeros, int ... values) {
		int result = 0;
		for(int i=0; i<values.length; i++)
			result+=numeros.get(i)*values[i];
		return result;
	}
	
}
