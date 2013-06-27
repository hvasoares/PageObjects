package com.github.pageobject.impl.el;

import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.FieldFactory;

public interface TestableElField extends Field {

	String getXpath();

	ElContext getElContext();

	 FieldFactory getFieldFactory();

}
