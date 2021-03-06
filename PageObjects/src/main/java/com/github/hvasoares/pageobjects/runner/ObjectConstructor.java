package com.github.hvasoares.pageobjects.runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ObjectConstructor {

	public <T> T construct(Class<? extends T> clazz) {
		try {
			
			Constructor<?> defaultConstructor = clazz.getConstructors()[0];
			for(Constructor<?> c : clazz.getConstructors())
				if(c.getParameterTypes().length==0){
					defaultConstructor = c;
					break;
				}
			return (T) defaultConstructor.newInstance();
		} catch (IllegalArgumentException e1) {
			throw new RuntimeException("The feature class must have one and only one default constructor",e1);
		} catch (InstantiationException e1) {
			throw new RuntimeException("The test class couldn't be instantiated",e1);
		} catch (IllegalAccessException e1) {
			throw new RuntimeException("The constructor of this class is private or protected",e1);
		} catch (InvocationTargetException e1) {
			throw new RuntimeException("Couldnt create the feature object check previous errors",e1);
		}
	}
}
