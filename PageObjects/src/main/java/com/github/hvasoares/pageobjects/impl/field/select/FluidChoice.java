package com.github.hvasoares.pageobjects.impl.field.select;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.Select;

public class FluidChoice implements Select {

	private String alias;
	private String rootXpath;
	private Browser browser;

	public FluidChoice(String alias, String rootXpath) {
		this.rootXpath = rootXpath;
		this.alias = alias;
	}

	@Override
	public void setBrowser(Browser value) {
		this.browser= value;
	}

	@Override
	public void fill(String string) {
		browser.click(this.rootXpath+"//option[normalize-space(text())='"+string+"']");
	}

	@Override
	public String getAlias() {
		return alias;
	}
	
}
