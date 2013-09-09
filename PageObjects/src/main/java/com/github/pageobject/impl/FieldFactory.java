package com.github.pageobject.impl;

public interface FieldFactory {

	Field createTextField(String string, String string2);

	Clickable createClickable(String string, String xpath, String toPageAlias);

	Clickable createClickable(String alias, String xpath);

	Field createFileField(String value, String xpath);

}
