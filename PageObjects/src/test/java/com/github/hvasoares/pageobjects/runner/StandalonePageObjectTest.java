package com.github.hvasoares.pageobjects.runner;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.RepositoryAwareFactory;
import com.github.hvasoares.pageobjects.runner.resources.SampleRepository;

public class StandalonePageObjectTest {

	private ObjectUnderTest objectUnderTest = new ObjectUnderTest();
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
	}};

	@Mock private ObjectConstructor objectConstructor;
	@Mock private RepositoryAwareFactory factory;
	
	@Test
	public void shouldConstructAClassOutsideJunit(){
		StandalonePageObject<ObjectUnderTest> instance = new StandalonePageObject<>(objectUnderTest,factory,objectConstructor);
		
		ctx.checking(new Expectations(){{
			oneOf(objectConstructor).construct(SampleRepository.class);
			PageObjectRepository sampleRepository = ctx.mock(PageObjectRepository.class);
			will(returnValue(sampleRepository));
			oneOf(factory).setRepository(sampleRepository);
		}});
				
		instance.getObject();
		
		assertTrue(objectUnderTest.isSetFactoryCalled());
		assertTrue(objectUnderTest.isSetAnotherFactoryAwareCalled());
		assertEquals(objectUnderTest.getFactory(),factory);
	}

}
