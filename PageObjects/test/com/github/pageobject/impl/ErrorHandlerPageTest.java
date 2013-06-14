package com.github.pageobject.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.pageobject.PageObject;

public class ErrorHandlerPageTest {

	private Mockery ctx;
	private PageObject innerPage;
	private ErrorHandlerPage inst;
	private RuntimeException expectedException;

	@Test
	public void shouldGetAnyExceptionFromFillAField() {
		ctx = new Mockery();
		innerPage = ctx.mock(PageObject.class);
		inst = new ErrorHandlerPage(innerPage);
		expectedException = new RuntimeException();
		
		ctx.checking(new Expectations(){{
			oneOf(innerPage).fill("someField","someValue");
			will(throwException(expectedException));
			
			oneOf(innerPage).getName();
			will(returnValue("somePage"));
		}});
	
		try{
			inst.fill("someField","someValue");
		}catch(RuntimeException ex){
			assertEquals(ex.getMessage(),
					"Some problems showed when trying to fill field " +
					"'someField' with value 'someValue' at page 'somePage'"
			);
			assertEquals(ex.getCause(),expectedException);
			ctx.assertIsSatisfied();
			return;
		}
		assertTrue("should not reach here",false);
	}

	@Test
	public void shouldGetAnyExceptionFromClick() {
		ctx = new Mockery();
		innerPage = ctx.mock(PageObject.class);
		inst = new ErrorHandlerPage(innerPage);
		expectedException = new RuntimeException();
		
		ctx.checking(new Expectations(){{
			oneOf(innerPage).click("someButton");
			will(throwException(expectedException));
			
			oneOf(innerPage).getName();
			will(returnValue("somePage"));
		}});
	
		try{
			inst.click("someButton");
		}catch(RuntimeException ex){
			assertEquals(ex.getMessage(),
					"Some problems showed when trying click button " +
					"'someButton' at page 'somePage'"
			);
			assertEquals(ex.getCause(),expectedException);
			ctx.assertIsSatisfied();
			return;
		}
		assertTrue("should not reach here",false);
	}
}
