package com.github.hvasoares.pageobjects.runner;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.runner.ClassReflectionUtilsImpl;
import com.github.hvasoares.pageobjects.runner.ObjectConstructor;
import com.github.hvasoares.pageobjects.runner.resources.SampleTest;

public class ClassReflectionUtilsImplTest {

	private Mockery ctx;
	private ClassReflectionUtilsImpl inst;
	private PageObjectBuilderFactory factory;

	@Test
	public void shouldInjectPageObject() {
		ctx = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		final ObjectConstructor constructor = ctx.mock(ObjectConstructor.class);
		factory = ctx.mock(AbstractFactory.class);
		
		inst = new ClassReflectionUtilsImpl(constructor,factory);
		final SampleTest test = new SampleTest();
		
		ctx.checking(new Expectations(){{
			oneOf(constructor).construct(SampleTest.class);
			will(returnValue(test));
		}});
		
		try {
			inst.run(SampleTest.class,SampleTest.class.getMethod("testMethod"));
			assertTrue(test.isTested());
			assertTrue(test.isInjected());
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
