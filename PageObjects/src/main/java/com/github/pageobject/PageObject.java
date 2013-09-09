package com.github.pageobject;


public interface PageObject {
	PageObject fill(String field, String value);
	PageObject click(String string);
	String getName();
}
