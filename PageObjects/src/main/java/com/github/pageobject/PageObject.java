package com.github.pageobject;

import com.github.pageobject.impl.Readability;


public interface PageObject extends AssertivePageObject {
	PageObject fill(String field, String value);
	PageObject click(String string);
	PageObject doubleClick(String alias);
	String getName();
	Readability readability();
}
