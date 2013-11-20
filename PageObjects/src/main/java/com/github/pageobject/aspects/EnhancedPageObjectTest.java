package com.github.pageobject.aspects;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.AbstractFactory;
import com.github.pageobject.Mutability;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Readability;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.runner.PageObjectFactoryAware;

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
