package com.github.pageobject.impl.mutability;

import com.github.pageobject.impl.browser.Browser;

public class FluidXpathFactory implements FluidXpathFactoryI{
	public Browser browser;

	@Override
	public FluidXpathI create(String xpath) {
		return new FluidXpath(xpath);
	}

}
