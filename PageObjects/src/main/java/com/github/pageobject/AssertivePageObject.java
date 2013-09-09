package com.github.pageobject;

public interface AssertivePageObject extends IncompletePageObject{
	public void pageAssertion(String xpath);
	public void addNamedAssertion(String name,String xpath);
	public void checkAssertion(String namedAssertion);
}
