package com.github.pageobject.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class PageObjectImplTest {

	private FieldContainer fieldCont;
	private ClickableContainer clickCont;
	private PageObjectImpl inst;
	private Mockery ctx;

	@Before
	public void setUp() {
		ctx = new Mockery();
		clickCont = ctx.mock(ClickableContainer.class);
		fieldCont = ctx.mock(FieldContainer.class);
		inst = new PageObjectImpl(clickCont,fieldCont);
	}
	
	@Test
	public void shouldDelegateFillAndClick() {
		ctx.checking(new Expectations(){{
			oneOf(fieldCont).fill("someAlias","someValue");
			oneOf(clickCont).click("someButton");
		}});
		
		inst.fill("someAlias","someValue");
		inst.click("someButton");
	
		ctx.assertIsSatisfied();
	}
	
	@Test
	public void shouldAddFieldsAndClickablesToContainers(){
		final Field field = ctx.mock(Field.class);
		final Clickable click = ctx.mock(Clickable.class);
		ctx.checking(new Expectations(){{
			oneOf(fieldCont).add(field);
			oneOf(clickCont).add(click);
		}});
		
		inst.addClickable(click);
		inst.addField(field);
	
		ctx.assertIsSatisfied();		
	}
	
}
