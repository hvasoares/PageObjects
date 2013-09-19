package com.github.pageobject.impl.mutability;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

class Utils {
	public static String returnIfMatchesExpression(String placeHolder){
		checkArgument(
				Pattern.matches("[a-zA-Z0-9]*", placeHolder),
				"The placeholder names must by in the format '"+placeHolder+"'"
		);
		return placeHolder;
	}
	
	public static Set<Entry<String, String>> toMapSetEntry(String[] array) {
		checkArgument(array.length%2==0, "The array length must be pair.");
		Map<String, String> result = new HashMap<String, String>();
		for(int i=0; i<array.length-1;i+=2){
			result.put(Utils.returnIfMatchesExpression(array[i]), array[i+1]);
		}
		return result.entrySet();
	}
}