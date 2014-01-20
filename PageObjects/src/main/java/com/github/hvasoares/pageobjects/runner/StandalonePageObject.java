package com.github.hvasoares.pageobjects.runner;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.hvasoares.pageobjects.RepositoryAwareFactory;

public class StandalonePageObject<T> {

	private T objectUnderTest;
	private RepositoryAwareFactory factory;
	private ObjectConstructor objectConstructor;

	public StandalonePageObject(T objectUnderTest, RepositoryAwareFactory factory, ObjectConstructor objectConstructor) {
		this.objectUnderTest = objectUnderTest;
		this.factory = factory;
		this.objectConstructor = objectConstructor;
	}

	public T getObject() {
		Class<? extends Object> clazz = objectUnderTest.getClass();
		
		checkArgument(clazz.isAnnotationPresent(PageObjectTest.class));
		
		Method[] methods = clazz.getMethods();

		factory.setRepository(getRepository());
		
		for(Method method : methods){
			if(method.isAnnotationPresent(PageObjectFactoryAware.class)){
				callFactoryAwareMethodMethod(objectUnderTest,method);
			}
		}	
		return objectUnderTest;
	}


	private PageObjectRepository getRepository() {
		PageObjectTest pot = objectUnderTest.getClass().getAnnotation(PageObjectTest.class);
		return objectConstructor.construct(pot.repository());
	}

	private void callFactoryAwareMethodMethod(Object objectUnderTest2, Method method) {
		try{
			method.setAccessible(true);
			method.invoke(objectUnderTest2, factory);
			method.setAccessible(false);
		}catch(InvocationTargetException|IllegalAccessException ex){
			throw new RuntimeException("Exception throwed while calling method " + method.getName(),ex);
		}
		
	}

}
