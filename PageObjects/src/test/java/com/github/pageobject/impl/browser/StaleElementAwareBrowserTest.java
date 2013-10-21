package com.github.pageobject.impl.browser;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;

public class StaleElementAwareBrowserTest {

	@Mock private Browser inner;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Test
	public void shouldRetryIfTheStaleElementExceptionIsThrownWhileFilling() {
		StaleElementAwareBrowser instance = new StaleElementAwareBrowser(inner);
		ctx.checking(new Expectations(){{
			oneOf(inner).fill("xpath", "value");
			will(throwException(new StaleElementReferenceException("mmm")));
			
			oneOf(inner).fill("xpath", "value");
		}});
		
		instance.fill("xpath","value");
	}
	
	@Test
	public void shouldRetryIfTheStaleElementExceptionIsThrownWhileClicking() {
		StaleElementAwareBrowser instance = new StaleElementAwareBrowser(inner);
		ctx.checking(new Expectations(){{
			oneOf(inner).click("xpath");
			will(throwException(new StaleElementReferenceException("mmm")));
			
			oneOf(inner).click("xpath");
		}});
		
		instance.click("xpath");
	}

}

