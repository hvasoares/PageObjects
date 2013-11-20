package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.Readability;


public interface PageObject extends AssertivePageObject {
	PageObject fill(String field, String value);
	PageObject click(String string);
	PageObject doubleClick(String alias);
	String getName();
	Readability readability();
}
