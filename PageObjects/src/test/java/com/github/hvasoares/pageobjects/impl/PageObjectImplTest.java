package com.github.hvasoares.pageobjects.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.ClickableContainer;
import com.github.hvasoares.pageobjects.impl.FieldContainer;
import com.github.hvasoares.pageobjects.impl.PageObjectImpl;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.Assertiveness;

public class PageObjectImplTest {

	private FieldContainer fieldCont;
	private ClickableContainer clickCont;
	private PageObjectImpl inst;
	private Mockery ctx;
	private Assertiveness assertiveness;

	@Before
	public void setUp() {
		ctx = new Mockery();
		clickCont = ctx.mock(ClickableContainer.class);
		fieldCont = ctx.mock(FieldContainer.class);
		assertiveness = ctx.mock(Assertiveness.class);
		inst = new PageObjectImpl(clickCont,fieldCont,assertiveness);
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
