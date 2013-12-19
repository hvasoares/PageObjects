package com.github.hvasoares.pageobjects.report;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@Ignore("Work in progress")
public class PageLoadedWaitTest {

	interface WebDriverToMock extends WebDriver,JavascriptExecutor{};

	private PageLoadedWait instance;
	@Mock private WebDriverToMock webDriver;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Test
	public void shouldReturnAfterThePageLoadsCompletely() {
		instance = new PageLoadedWait();
	
		ctx.checking(new Expectations(){{
			oneOf(webDriver).executeScript("return document.readyState");
			will(returnValue("complete"));
		}});
		
		assertTrue(
				instance.getExpectation().apply(webDriver)
		);
	}
	
	@Test
	public void shouldRun(){
		instance = new PageLoadedWait();
		fail("You should create a kind of factory for the wait dependency");
	}

}
