package com.github.pageobject;

import com.github.pageobject.impl.Readability;



public interface StatePageObject {

	void setState(String stateName);

	StatePageObject checkAssertion(String namedAssertion);

	StatePageObject click(String alias);

	StatePageObject fill(String field, String value);
	
	Readability readability();

	String getName();

	Mutability mutability();

}
