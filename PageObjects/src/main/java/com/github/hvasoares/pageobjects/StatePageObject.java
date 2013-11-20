package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.Readability;



public interface StatePageObject {

	void setState(String stateName);

	StatePageObject checkAssertion(String namedAssertion);

	StatePageObject click(String alias);
	
	StatePageObject doubleClick(String alias);

	StatePageObject fill(String field, String value);
	
	Readability readability();

	String getName();

	Mutability mutability();

}
