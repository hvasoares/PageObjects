package com.github.pageobject;

public interface PageObjectBuilder {

	PageObjectBuilder addClickable(String string, String string2);

	PageObjectBuilder addTextField(String string, String string2);

	PageObject get();

	PageObjectBuilder addClickable(String alias, String xpath, String toPage);

	PageObjectBuilder setName(String value);

}
