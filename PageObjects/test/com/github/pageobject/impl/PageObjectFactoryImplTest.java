package com.github.pageobject.impl;
import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObject;


public class PageObjectFactoryImplTest {

	private Mockery ctx;
	private FieldFactory fieldFactory;
	private IncompletePageObject pageO;
	private PageObjectFactoryImpl inst;

	@Before
	public void setUp(){
		ctx = new Mockery();
		pageO = ctx.mock(IncompletePageObject.class);
		fieldFactory = ctx.mock(FieldFactory.class);
		inst = new PageObjectFactoryImpl(fieldFactory);
	}
	
	@Test
	public void shouldAddFieldToThePage() {		
		ctx.checking(new Expectations(){{
			Field newField = ctx.mock(Field.class);
			oneOf(fieldFactory).createTextField("fieldname","xpath");
			will(returnValue(newField));
			oneOf(pageO).addField(newField);
		}});		
		PageObject result = inst.startBuild(pageO)
			.addTextField("fieldname","xpath")
			.get();
		ctx.assertIsSatisfied();
		assertEquals(result,pageO);
	}
	
	@Test
	public void shouldAddClickableToPage(){
		ctx.checking(new Expectations(){{
			Clickable click = ctx.mock(Clickable.class,"first click");
			oneOf(fieldFactory).createClickable("buttonName","xpath","toPageAlias");
			will(returnValue(click));
			oneOf(pageO).addClickable(click);
			
			Clickable click2 = ctx.mock(Clickable.class,"second click");
			oneOf(fieldFactory).createClickable("buttonName2","xpath2");
			will(returnValue(click2));
			oneOf(pageO).addClickable(click2);

		}});
		PageObject result = inst.startBuild(pageO)
			.addClickable("buttonName","xpath","toPageAlias")
			.addClickable("buttonName2","xpath2")
			.get();
		ctx.assertIsSatisfied();
		assertEquals(result,pageO);
	}


}
