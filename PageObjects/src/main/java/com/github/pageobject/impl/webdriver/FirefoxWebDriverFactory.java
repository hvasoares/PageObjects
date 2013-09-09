package com.github.pageobject.impl.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriverFactory implements WebDriverFactory{

	@Override
	public WebDriver create() {
		return new FirefoxDriver();
	}

}
