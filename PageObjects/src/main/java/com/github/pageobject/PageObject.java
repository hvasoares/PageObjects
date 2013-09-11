package com.github.pageobject;


public interface PageObject extends AssertivePageObject {
	PageObject fill(String field, String value);
	PageObject click(String string);
	String getName();
}
