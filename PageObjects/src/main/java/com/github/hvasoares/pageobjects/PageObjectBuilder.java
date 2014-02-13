package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.field.CustomField;

public interface PageObjectBuilder {

	PageObjectBuilder addClickable(String alias, String xpath);

	PageObjectBuilder addTextField(String alias, String xpath);

	PageObject get();

	PageObjectBuilder addClickable(String alias, String xpath, String toPage);

	PageObjectBuilder setName(String value);

	PageObjectBuilder addFileField(String alias, String xpath);

	PageObjectBuilder addCustomField(CustomField custom);

	PageObjectBuilder addNamedAssert(String name, String xpath);
	
	Readability readability();
	
	Mutability mutability();
	
	Automata automata();
}
