package com.github.pageobject.impl.field;

import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.browser.Browser;

public class TextField implements Field{

	private String alias;
	private String xpath;
	private Browser browser;

	public TextField(String alias, String xpath, Browser browser) {
		this.alias = alias;
		this.xpath = xpath;
		this.browser = browser;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void fill(String value) {
		browser.fill(xpath,value);
	}

}
