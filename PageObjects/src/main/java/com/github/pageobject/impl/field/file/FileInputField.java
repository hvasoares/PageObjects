package com.github.pageobject.impl.field.file;

import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.CustomField;

public class FileInputField implements CustomField{

	private PathGenerator resourceLoader;
	private Browser browser;
	private String xpath;
	private String alias;

	public FileInputField(String alias, String xpath,Browser browser, PathGenerator resourceLoader) {
		this(alias,xpath,resourceLoader);
		this.browser = browser;
	}
	
	public FileInputField(String alias, String xpath,PathGenerator resourceLoader){
		this.alias=alias;
		this.xpath = xpath;
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

	@Override
	public void setBrowser(Browser value) {
		this.browser = value;
	}

}
