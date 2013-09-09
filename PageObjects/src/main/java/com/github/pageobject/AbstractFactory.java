package com.github.pageobject;

import com.github.pageobject.impl.browser.Browser;

public interface AbstractFactory extends PageObjectBuilderFactory{
	public abstract Browser getBrowser();

	public abstract StatePageObject getStateObject();
}
