package com.github.pageobject.impl.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriverFactory implements WebDriverFactory{

	@Override
	public WebDriver create() {
		FirefoxDriver result = new FirefoxDriver();
		result.manage().window().maximize();
		return result;
	}

}
