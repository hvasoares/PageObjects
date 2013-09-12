package com.github.pageobject;

import com.github.pageobject.impl.Readability;
import com.github.pageobject.impl.field.CustomField;

public interface PageObjectBuilder {

	PageObjectBuilder addClickable(String string, String string2);

	PageObjectBuilder addTextField(String string, String string2);

	PageObject get();

	PageObjectBuilder addClickable(String alias, String xpath, String toPage);

	PageObjectBuilder setName(String value);

	PageObjectBuilder addFileField(String string, String string2);

	PageObjectBuilder addCustomField(CustomField custom);

	PageObjectBuilder addNamedAssert(String name, String xpath);
	
	Readability readability();

}
