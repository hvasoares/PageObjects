package com.github.hvasoares.pageobjects.report;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.hvasoares.pageobjects.aspects.WebDriverAware;

public class PageLoadedWait implements WebDriverAware {
	
	private ExpectedCondition<Boolean> expectation;

	public PageLoadedWait() {
		expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver)
						.executeScript("return document.readyState").equals("complete");
			}
		};
	}

	public void waitForPageLoaded(){		
		waitForPageLoaded(30);
	}
	
	public void waitForPageLoaded( int timeOutInSeconds ) {
		WebDriver webDriver = getWebDriver();
				
		Wait<WebDriver> wait = new WebDriverWait( webDriver , timeOutInSeconds );
		try {
			wait.until(expectation);
		} catch(Throwable error) {
			throw new TimeoutException( "We waited for "+ timeOutInSeconds +" and the page " + webDriver.getCurrentUrl() + " took too long to load!" );			 
		}
	}

	public ExpectedCondition<Boolean> getExpectation() {
		return expectation;
	} 
}
