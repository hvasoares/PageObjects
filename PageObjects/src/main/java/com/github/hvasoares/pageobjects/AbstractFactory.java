package com.github.hvasoares.pageobjects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.impl.browser.Browser;

public interface AbstractFactory extends PageObjectBuilderFactory{
	Browser getBrowser();
	StatePageObject getStateObject();
	WebDriver getWebDriver();
}
