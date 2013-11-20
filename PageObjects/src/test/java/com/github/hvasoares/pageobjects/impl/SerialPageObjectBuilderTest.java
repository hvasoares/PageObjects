package com.github.hvasoares.pageobjects.impl;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.impl.SerialPageObjectBuilder;

public class SerialPageObjectBuilderTest {

	private PageObject po1;
	private PageObject po2;

	@Test
	public void shouldCreateVariousPageObjects() {
		Mockery ctx = new Mockery();
		final PageObjectBuilderFactory factory = ctx.mock(PageObjectBuilderFactory.class);
		SerialPageObjectBuilder inst = new SerialPageObjectBuilder(factory);
		final PageObjectBuilder builder1 = ctx.mock(PageObjectBuilder.class,"builder1");
		final PageObjectBuilder builder2 = ctx.mock(PageObjectBuilder.class,"builder2");
		po1 = ctx.mock(PageObject.class,"page1");
		po2 = ctx.mock(PageObject.class,"page2");
		
		ctx.checking(new Expectations(){{
			atLeast(2).of(factory).createPageObjectBuilder();
			will(onConsecutiveCalls(
					returnValue(builder1),
					returnValue(builder2)
			));
			
			oneOf(builder1).setName("page1");
			oneOf(builder2).setName("page2");

			
			oneOf(builder1).get();
			will(returnValue(po1));
			
			oneOf(builder2).get();
			will(returnValue(po2));
		}});
		
		assertEquals(inst.newPage("page1"),builder1);
		assertEquals(inst.newPage("page2"),builder2);

		assertEquals(inst.getAll().get(0),po1);
		assertEquals(inst.getAll().get(1),po2);
		ctx.assertIsSatisfied();
	}

}
