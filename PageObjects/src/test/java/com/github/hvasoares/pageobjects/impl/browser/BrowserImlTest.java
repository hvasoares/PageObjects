package com.github.hvasoares.pageobjects.impl.browser;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.browser.BrowserImpl;

public class BrowserImlTest {

	private Mockery ctx;
	private WebDriver driver;
	private BrowserImpl inst;

	@Before
	public void setUp() {
		ctx = new Mockery();
		driver = ctx.mock(WebDriver.class);
		inst = new BrowserImpl(driver);
		ctx.checking(new Expectations(){{
			allowing(driver).get("someUrl");
		}});
		inst.goToStartUrl("someUrl");
	}
	
	@Test
	public void shouldUseTheDriverToClick() {
		ctx.checking(new Expectations(){{
			WebElement clickable = ctx.mock(WebElement.class);
			oneOf(driver).findElement(with(any(By.ByXPath.class)));
			will(returnValue(clickable));
			oneOf(clickable).click();
		}});
		inst.click("//input");
		ctx.assertIsSatisfied();
	}
	
	@Test
	public void shouldUseTheDriverToFillTextFields(){
		ctx.checking(new Expectations(){{
			WebElement textField = ctx.mock(WebElement.class);
			oneOf(driver).findElement(with(any(By.ByXPath.class)));
			will(returnValue(textField));

			oneOf(textField).sendKeys("someValue");
			
		}});
		
		inst.fill("/someXpath","someValue");
		
		ctx.assertIsSatisfied();
	}
	
	@Test 
	public void shouldStartTheDriverGivenAUrl(){
		final WebDriver newWD = ctx.mock(WebDriver.class,"custom");
		Browser newInst = new BrowserImpl(newWD);
		ctx.checking(new Expectations(){{
			oneOf(newWD).get("someUrl");
		}});
		newInst.goToStartUrl("someUrl");
		ctx.assertIsSatisfied();
	}
	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionIfTheBrowserIsNotStarted(){
		final WebDriver newWD = ctx.mock(WebDriver.class,"custom");
		BrowserImpl newInst = new BrowserImpl(newWD);
		newInst.assertValidState();
	}
	@Test(expected=RuntimeException.class)
	public void shouldTrhowExceptionIfTheBrowserIsClosed(){
		ctx.checking(new Expectations(){{
			oneOf(driver).close();
			oneOf(driver).quit();
		}});
		inst.close();
		ctx.assertIsSatisfied();
		inst.assertValidState();
	}

}
