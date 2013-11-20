package com.github.hvasoares.pageobjects.impl.field;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.ClickableImpl;
import com.github.hvasoares.pageobjects.impl.field.ClickableParams;

public class ClickableImplTest {

	private Mockery ctx;
	private Browser browser;
	private StatePageObject statePageObject;
	private Clickable inst;

	@Test
	public void shouldUseTheBrowserToClick() {
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		statePageObject = ctx.mock(StatePageObject.class);
		inst = new ClickableImpl(
				new ClickableParams("alias","xpath"),
				browser,
				statePageObject
		);
		assertEquals("alias",inst.getAlias());
		ctx.checking(new Expectations(){{
			oneOf(browser).click("xpath");
		}});
		
		inst.click();
		
		ctx.assertIsSatisfied();
	}
	
	@Test
	public void shouldUseTheBrowserAndSetTheNextState(){
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		statePageObject = ctx.mock(StatePageObject.class);
		inst = new ClickableImpl(
				new ClickableParams("alias","xpath","toPage"),
				browser,
				statePageObject
		);
		assertEquals("alias",inst.getAlias());
		ctx.checking(new Expectations(){{
			oneOf(browser).click("xpath");
			oneOf(statePageObject).setState("toPage");
		}});
		
		inst.click();
		
		ctx.assertIsSatisfied();
	}

}
