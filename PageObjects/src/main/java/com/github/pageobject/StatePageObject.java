package com.github.pageobject;



public interface StatePageObject {

	void setState(String stateName);

	StatePageObject checkAssertion(String namedAssertion);

	StatePageObject click(String alias);

	StatePageObject fill(String field, String value);

	String getName();

}
