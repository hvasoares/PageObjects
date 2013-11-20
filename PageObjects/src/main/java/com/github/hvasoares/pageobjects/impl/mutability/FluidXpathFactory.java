package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.impl.browser.Browser;

public class FluidXpathFactory implements FluidXpathFactoryI{
	public Browser browser;

	@Override
	public FluidXpathI create(String xpath) {
		return new FluidXpath(xpath);
	}

}
