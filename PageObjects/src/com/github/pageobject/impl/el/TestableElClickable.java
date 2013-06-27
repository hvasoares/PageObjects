package com.github.pageobject.impl.el;

import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.FieldFactory;

public interface TestableElClickable extends Clickable {

	FieldFactory getFieldFactory();

	ElContext getElContext();

	String getXpath();

	public abstract String getToPageAlias();

}
