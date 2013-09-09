package com.github.pageobject;

import com.github.pageobject.impl.Field;

public interface PageObjectBuilder {

	PageObjectBuilder addClickable(String string, String string2);

	PageObjectBuilder addTextField(String string, String string2);

	AssertivePageObject get();

	PageObjectBuilder addClickable(String alias, String xpath, String toPage);

	PageObjectBuilder setName(String value);
	
	PageObjectBuilder addCustomField(Field custom);

	PageObjectBuilder addFileField(String string, String string2);

}
