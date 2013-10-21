package com.github.pageobject;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.browser.Browser;

public interface AbstractFactory extends PageObjectBuilderFactory{
	Browser getBrowser();
	StatePageObject getStateObject();
	WebDriver getWebDriver();
}
