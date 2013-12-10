package com.github.hvasoares.pageobjects.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public static <K,V> Map<K,V> merge(Map<K,V> a, Object ... values) {
		Map<K, V> result = new HashMap<>();
		Map<K, V> b = hashMap(values); 
		for(Entry<K, V> e : a.entrySet())
			result.put(e.getKey(), e.getValue());
		
		for(Entry<K, V> e : b.entrySet())
			result.put(e.getKey(), e.getValue());
		return result;
	}

	public static <K,V> Map<K, V> projection(Map<K,V> from,K ... keys) {
		Map<K, V> result = new HashMap<>();
		for(K k : keys)
			result.put(k, from.get(k));
		return result;
	}
}
