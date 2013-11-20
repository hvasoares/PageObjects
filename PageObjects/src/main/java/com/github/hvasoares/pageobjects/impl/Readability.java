package com.github.hvasoares.pageobjects.impl;

import java.util.Map;

public interface Readability {

	public abstract void setProperty(String propertyName, String xpathValue);

	public abstract String read(String propertyName);

	public abstract Map<String,String> getDb();

}
