package com.github.hvasoares.pageobjects.aspects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.runner.PageObjectFactoryAware;

public interface EnhancedPageObjectTest {
	StatePageObject getStatePageObject();

	StatePageObject checkAssertion(String namedAssertion);

	StatePageObject click(String alias);
	
	StatePageObject doubleClick(String alias);

	StatePageObject fill(String field, String value);
	
	Readability readability();

	Mutability mutability();
	
	Browser getBrowser();
	AbstractFactory getFactory();
	
	@PageObjectFactoryAware
	void setFactory(AbstractFactory value);
	
	WebDriver getWebDriver();
	
}
