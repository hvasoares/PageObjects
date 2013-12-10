package com.github.hvasoares.pageobjects.aspects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.WebDriverHolder;
 
 
public aspect WebDriverAwareInject {
	
	public WebDriver WebDriverAware.webDriver = null;
	
	public WebDriver WebDriverAware.getWebDriver(){
		webDriver = WebDriverHolder.getWebDriver();
		return webDriver;
	} 
}
