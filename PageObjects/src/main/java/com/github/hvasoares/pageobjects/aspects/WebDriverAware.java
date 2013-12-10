package com.github.hvasoares.pageobjects.aspects;

import org.openqa.selenium.WebDriver;

public interface WebDriverAware {
	public void setWebDriver(WebDriver value);
	public WebDriver getWebDriver();
}
