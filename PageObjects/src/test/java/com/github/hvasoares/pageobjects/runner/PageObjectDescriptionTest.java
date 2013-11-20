package com.github.hvasoares.pageobjects.runner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

import com.github.hvasoares.pageobjects.runner.ObjectConstructor;
import com.github.hvasoares.pageobjects.runner.PageObjectDescription;
import com.github.hvasoares.pageobjects.runner.resources.SampleRepository;
import com.github.hvasoares.pageobjects.runner.resources.SampleTest;
import com.github.jsteak.DescriptionGetter;

public class PageObjectDescriptionTest {

	private Mockery ctx;
	private DescriptionGetter runner;
	private PageObjectDescription inst;
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
