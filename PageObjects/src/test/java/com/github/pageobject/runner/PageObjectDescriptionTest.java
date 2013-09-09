package com.github.pageobject.runner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.github.jsteak.DescriptionGetter;
import com.github.pageobject.runner.resources.SampleRepository;
import com.github.pageobject.runner.resources.SampleTest;

public class PageObjectDescriptionTest {

	private Mockery ctx;
	private DescriptionGetter runner;
	private PageObjectDescription inst;
	private RunNotifier notifier;
	private ObjectConstructor objectConstructor;
	@Before
	public void setUp() {
		ctx = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		runner = ctx.mock(DescriptionGetter.class);
		objectConstructor = new ObjectConstructor();
		inst = new PageObjectDescription(runner,objectConstructor,SampleTest.class);
	}
	
	@Test
	public void givenAClazzItShouldGetTheClassRepository() {		
		final Description desc = ctx.mock(Description.class);
		ctx.checking(new Expectations(){{
			oneOf(runner).getDescription();
			will(returnValue(desc));
		}});
		assertEquals(inst.getDescription(),desc);
		ctx.assertIsSatisfied();
		assertTrue(inst.getRepository() instanceof SampleRepository);
	}
}
