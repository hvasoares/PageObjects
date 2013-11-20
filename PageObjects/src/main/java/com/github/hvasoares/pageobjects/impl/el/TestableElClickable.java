package com.github.hvasoares.pageobjects.impl.el;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.FieldFactory;

public interface TestableElClickable extends Clickable {

	FieldFactory getFieldFactory();

	ElContext getElContext();

	String getXpath();

	public abstract String getToPageAlias();

}
