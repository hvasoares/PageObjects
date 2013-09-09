package com.github.pageobject.impl;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.PageObject;

public class PrototypePageObjectImplTest {

	private Mockery ctx;
	private PageObject clone;
	private PrototypePageObjectImpl inst;
	private PageObject innerObject;

	@Before
	public void setUp() {
		ctx = new Mockery();
		clone = ctx.mock(PageObject.class,"clone");
		innerObject = ctx.mock(PageObject.class,"inner");
		inst = new PrototypePageObjectImpl(innerObject,clone);
	}
	
	@Test
	public void shouldDelegateTheCall() {
		ctx.checking(new Expectations(){{
			oneOf(clone).fill("someField","someValue");
			oneOf(clone).click("someButton");
		}});		
		
		inst.fill("someField","someValue")
			.click("someButton");
		
		ctx.assertIsSatisfied();
	}
	
	@Test(expected=RuntimeException.class)
	public void ifTheClickableIsInObjectShouldBeCalled(){
		ctx.checking(new Expectations(){{
			oneOf(clone).click("someButton");
			will(throwException(new RuntimeException()));
			
			oneOf(innerObject).click("someButton");
			will(throwException(new RuntimeException()));
		}});
		
		inst.click("someButton");
		
		ctx.assertIsSatisfied();
	}
	@Test(expected=RuntimeException.class)
	public void ifTheFieldIsInObjectShould(){
		ctx.checking(new Expectations(){{
			oneOf(clone).fill("someField","someValue");
			will(throwException(new RuntimeException()));
			
			oneOf(innerObject).fill("someField","someValue");
			will(throwException(new RuntimeException()));
		}});
		
		inst.fill("someField","someValue");
		
		ctx.assertIsSatisfied();
	}
	
	@Test
	public void theNameOfPageIsTheNameOfInnerObject(){
		ctx.checking(new Expectations(){{
			oneOf(innerObject).getName();
			will(returnValue("aName"));
		}});
		assertEquals("aName",inst.getName());
		ctx.assertIsSatisfied();
	}

}
