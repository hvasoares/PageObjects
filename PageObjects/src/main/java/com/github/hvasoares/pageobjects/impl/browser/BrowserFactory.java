package com.github.hvasoares.pageobjects.impl.browser;

import org.openqa.selenium.WebDriver;

public abstract class BrowserFactory {
	private static WebDriver currentDriver;
	private static TestSuiteAwareBrowser instance;

	public static Browser createBrowser(WebDriver driver){
		if(currentDriver!=null || currentDriver!=driver){
			instance = TestSuiteAwareBrowser.getInstance(
					new StaleElementAwareBrowser( new BrowserImpl(driver) )
			);
			currentDriver = driver;
		}
		return instance ;
	}
}
