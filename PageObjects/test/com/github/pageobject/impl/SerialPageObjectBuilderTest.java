package com.github.pageobject.impl;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.PageObjectBuilderFactory;

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
			oneOf(builder1).addClickable("someAlias1","someXpath1");
			oneOf(builder1).addClickable("someAlias1.1","someXpath1.1","action");
			oneOf(builder1).addTextField("someField1","someXpathField1");
			
			oneOf(builder2).setName("page2");
			oneOf(builder2).addClickable("someAlias2","someXpath2");
			
			oneOf(builder1).get();
			will(returnValue(po1));
			
			oneOf(builder2).get();
			will(returnValue(po2));
		}});
		
		inst.newPage("page1")
			.addClickable("someAlias1","someXpath1")
			.addClickable("someAlias1.1","someXpath1.1","action")
			.addTextField("someField1","someXpathField1");
		inst.newPage("page2")
			.addClickable("someAlias2","someXpath2");
		assertEquals(inst.getAll().get(0),po1);
		assertEquals(inst.getAll().get(1),po2);
		ctx.assertIsSatisfied();
	}

}
