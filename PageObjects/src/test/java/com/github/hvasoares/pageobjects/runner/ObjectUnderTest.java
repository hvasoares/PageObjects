package com.github.hvasoares.pageobjects.runner;

import static com.google.common.base.Preconditions.checkNotNull;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.runner.resources.SampleRepository;

@PageObjectTest(repository=SampleRepository.class)
public class ObjectUnderTest {

	private boolean setFactoryCalled;
	private boolean setAnotherFactoryAwareCalled;
	private AbstractFactory factory;
	
	public AbstractFactory getFactory() {
		return factory;
	}

	public ObjectUnderTest() {
		setFactoryCalled = false;
		setAnotherFactoryAwareCalled = false;
	}

	@PageObjectFactoryAware
	public void setFactory(AbstractFactory value) {
		checkNotNull(value);
		factory = value;
		setFactoryCalled = true;
	}
	
	@PageObjectFactoryAware
	public void setAnotherFactoryAware(AbstractFactory value){
		checkNotNull(value);
		factory = value;
		setAnotherFactoryAwareCalled = true;
	}

	public boolean isSetFactoryCalled() {
		return setFactoryCalled	;
	}

	public boolean isSetAnotherFactoryAwareCalled() {
		return setAnotherFactoryAwareCalled;
	}
}
