package com.github.pageobject.impl.field;

import org.openqa.selenium.WebDriver;

public interface WebDriverAwareCustomField extends CustomField{
	void setWebDriver(WebDriver value);
}
