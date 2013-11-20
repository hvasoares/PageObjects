package com.github.hvasoares.pageobjects.impl.el;

import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.FieldFactory;

public interface TestableElField extends Field {

	String getXpath();

	ElContext getElContext();

	 FieldFactory getFieldFactory();

}
