package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.Field;

public interface IncompletePageObject extends PageObject{
	void addField(Field newField);
	void addClickable(Clickable click);
	void setName(String value);
}
