package com.github.hvasoares.pageobjects.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Coolections {

	public static Collection<Integer> times(int i){
		checkArgument(i>=0, "The i argument must be equals or greather than zero");
		Integer[] result = new Integer[i];
		for(int j=0; j<i; j++)
			result[j] = j;
		return Arrays.asList(result);
	}
	
	public static <K,V> Map<K,V> hashMap(Object ... values){
		checkArgument(values.length%2==0, "The values lenght cannot be a odd number");
		HashMap<K,V> result = new HashMap<>();

		for(int i=0; i<values.length; i+=2){
			result.put((K)values[i], (V)values[i+1]);
		}
		
		return result;
	}
}
