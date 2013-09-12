package com.github.pageobject.impl;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.impl.field.CustomField;
import com.github.pageobject.proxy.DecoratorObject;

public class ProxyPageObjectBuilderAdapter extends PageObjectBuilderSymbolTable implements DecoratorObject<PageObjectBuilderSymbolTable> {

	protected PageObjectBuilderSymbolTable inner;

	public PageObjectBuilder addClickable(String string, String string2) {
		return inner.addClickable(string, string2);
	}

	public PageObjectBuilder startBuild(IncompletePageObject pageObject) {
		return inner.startBuild(pageObject);
	}

	public PageObjectBuilder addTextField(String string, String string2) {
		return inner.addTextField(string, string2);
	}

	public PageObject get() {
		return inner.get();
	}

	public PageObjectBuilder addClickable(String alias, String xpath,
			String toPage) {
		return inner.addClickable(alias, xpath, toPage);
	}

	public PageObjectBuilder setName(String value) {
		return inner.setName(value);
	}

	public PageObjectBuilder addFileField(String string, String string2) {
		return inner.addFileField(string, string2);
	}

	public PageObjectBuilder addCustomField(CustomField custom) {
		return inner.addCustomField(custom);
	}

	public PageObjectBuilder addNamedAssert(String name, String xpath) {
		return inner.addNamedAssert(name, xpath);
	}

	public boolean equals(Object obj) {
		return inner.equals(obj);
	}

	public String toString() {
		return inner.toString();
	}

	public PageObjectBuilderSymbolTable getInner() {
		return inner;
	}

	public void setInner(PageObjectBuilderSymbolTable inner) {
		this.inner = inner;
	}
	
	public PageObjectBuilderSymbolTable self(){
		return this;
	}
	
	
}
