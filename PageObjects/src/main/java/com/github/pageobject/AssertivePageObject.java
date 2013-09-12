package com.github.pageobject;

import com.github.pageobject.impl.Readability;

public interface AssertivePageObject<T>{
	public void pageAssertion(String xpath);
	public void addNamedAssertion(String name,String xpath);
	public void checkAssertion(String namedAssertion);
	void setReadability(Readability value);
}
