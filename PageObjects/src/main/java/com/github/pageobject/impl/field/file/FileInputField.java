package com.github.pageobject.impl.field.file;

import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.browser.Browser;

public class FileInputField implements Field{

	private PathGenerator resourceLoader;
	private Browser browser;
	private String xpath;
	private String alias;

	public FileInputField(String alias, String xpath,Browser browser, PathGenerator resourceLoader) {
		this.alias=alias;
		this.xpath = xpath;
		this.browser = browser;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void fill(String value) {
		browser.fill(xpath,resourceLoader.generateFromResourceName(value));
	}

	@Override
	public String getAlias() {
		return alias;
	}

}
