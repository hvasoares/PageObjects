package com.github.pageobject.impl.browser;


public interface Browser {

	void click(String string);

	void fill(String xpath, String value);

	void goToStartUrl(String url);

	public abstract void close();

}
