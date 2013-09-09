package com.github.pageobject.impl.field;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.pageobject.impl.browser.Browser;

public class TextFieldTest {

	private Browser browser;
	private Mockery ctx;

	@Test
	public void test() {
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		TextField inst = new TextField("alias","xpath",browser);
		ctx.checking(new Expectations(){{
			oneOf(browser).fill("xpath","someText");
		}});
		
		assertEquals(inst.getAlias(),"alias");
		inst.fill("someText");
		ctx.assertIsSatisfied();
	}

}
