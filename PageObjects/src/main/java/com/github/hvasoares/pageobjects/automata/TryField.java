package com.github.hvasoares.pageobjects.automata;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.impl.browser.Browser;

public interface TryField {

	void setWebDriver(WebDriver value);

	boolean filled(String xpath, String value);

	void setBrowser(Browser value);

}
