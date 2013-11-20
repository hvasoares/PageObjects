package com.github.hvasoares.pageobjects.impl.browser;


public interface Browser {

	void click(String xpath);

	void fill(String xpath, String value);

	void goToStartUrl(String url);

	public abstract void close();

}
