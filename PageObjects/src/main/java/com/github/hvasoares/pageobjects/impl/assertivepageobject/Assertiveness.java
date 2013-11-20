package com.github.hvasoares.pageobjects.impl.assertivepageobject;

public interface Assertiveness {

	void pageAssertion(String xpath);

	void addNamedAssertion(String name, String xpath);

	void checkAssertion(String namedAssertion);

}
