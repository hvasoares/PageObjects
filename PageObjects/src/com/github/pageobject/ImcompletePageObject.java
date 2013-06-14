package com.github.pageobject;

import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.Field;

public interface ImcompletePageObject extends PageObject{
	void addField(Field newField);
	void addClickable(Clickable click);
	void setName(String value);
}
