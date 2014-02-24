package com.github.hvasoares.pageobjects.runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.hvasoares.jsteak.ClassReflectionUtils;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;

public class ClassReflectionUtilsImpl implements ClassReflectionUtils{
	private ObjectConstructor constructor;
	private PageObjectBuilderFactory factory;

	public ClassReflectionUtilsImpl(ObjectConstructor constructor,PageObjectBuilderFactory factory) {
		this.constructor =constructor;
		this.factory = factory;
	}
	@Override
	public void run(Class<?> class1, Method succesfull) {
		boolean notSetted = true;
		Object obj = constructor.construct(class1);
		for(Method m : class1.getMethods()){
			if(m.isAnnotationPresent(PageObjectFactoryAware.class)){
				notSetted = false;
				invoke(obj,m,factory);
			}
		}
		if(notSetted)
			throw new RuntimeException(
					"There's no method annotated with PageObjectFactoryAware"
			);
		invoke(obj,succesfull);
	}

	private void invoke(Object obj, Method m, Object ... args) {
		try {
			if(args.length==0)
				m.invoke(obj);
			else
				m.invoke(obj,args);
		} catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
			throw new RuntimeException("Could not call the method " + m.getName(),e);
		}
	}
}
