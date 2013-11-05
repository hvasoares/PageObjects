package com.github.pageobject.runner;

import org.junit.runner.Description;
import org.junit.runner.RunWith;

import com.github.jsteak.DescriptionGetter;

public class PageObjectDescription implements DescriptionGetter{
	private DescriptionGetter descGetter;
	private Class<?> clazz;
	private PageObjectRepository repository;
	private ObjectConstructor constructor;
		
	public PageObjectDescription(DescriptionGetter descGetter, ObjectConstructor objectConstructor, Class<?> clazz) {
		super();
		this.descGetter = descGetter;
		this.clazz = clazz;
		this.constructor = objectConstructor;
	}
	@Override
	public Description getDescription() {
		return descGetter.getDescription();
	}
	public PageObjectRepository getRepository() {
		if(repository!=null)
			return repository;
		
		boolean clazzIsSuite = clazz.isAnnotationPresent(RunWith.class) &&
				clazz.getAnnotation(RunWith.class).value() == PageObjectSuite.class;
		
		if(clazzIsSuite)
			return null;
		
		if(!clazz.isAnnotationPresent(PageObjectTest.class))
			throw new RuntimeException(
					"The test class should be annotated with PageObjectTest"
			);
		
		PageObjectTest por = clazz.getAnnotation(PageObjectTest.class);
		repository = constructor.construct(por.repository());
		return repository;
	}
	
}
