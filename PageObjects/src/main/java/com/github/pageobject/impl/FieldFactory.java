package com.github.pageobject.impl;

import com.github.pageobject.impl.field.CustomField;

public interface FieldFactory extends CustomFieldConfigurator{

	Field createTextField(String alias, String xpath);

	Clickable createClickable(String alias, String xpath, String toPageAlias);

	Clickable createClickable(String alias, String xpath);

	Field createFileField(String value, String xpath);

	Field createCustomField(CustomField custom);

}
