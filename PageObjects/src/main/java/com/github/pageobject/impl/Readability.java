package com.github.pageobject.impl;

public interface Readability {

	public abstract void setProperty(String propertyName, String xpathValue);

	public abstract String read(String propertyName);

}
