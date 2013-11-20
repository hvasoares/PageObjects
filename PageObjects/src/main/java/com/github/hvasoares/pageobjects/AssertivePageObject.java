package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.Readability;

public interface AssertivePageObject<T>{
	public void pageAssertion(String xpath);
	public void addNamedAssertion(String name,String xpath);
	public void checkAssertion(String namedAssertion);
	void setReadability(Readability value);
}
