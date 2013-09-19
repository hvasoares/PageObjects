package com.github.pageobject.impl;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.impl.field.CustomField;
import com.github.pageobject.proxy.DecoratorObject;

public class ProxyPageObjectBuilderAdapter extends PageObjectBuilderSymbolTable implements DecoratorObject<PageObjectBuilderSymbolTable> {

	private PageObjectBuilderSymbolTable inner;
	private DecoratorObject<PageObjectBuilderSymbolTable> outer;

	public PageObjectBuilder addClickable(String string, String string2) {
		getInner().addClickable(string, string2);
		return this;
	}

	public PageObjectBuilder startBuild(IncompletePageObject pageObject) {
		getInner().startBuild(pageObject);
		return this;
	}

	public PageObjectBuilder addTextField(String string, String string2) {
		getInner().addTextField(string, string2);
		return this;

	}

	public PageObject get() {
		return getInner().get();
	}

	public PageObjectBuilder addClickable(String alias, String xpath,String toPage) {
		getInner().addClickable(alias, xpath, toPage);
		return this;

	}

	public PageObjectBuilder setName(String value) {
		getInner().setName(value);
		return this;
	}

	public PageObjectBuilder addFileField(String string, String string2) {
		getInner().addFileField(string, string2);
		return this;
	}

	public PageObjectBuilder addCustomField(CustomField custom) {
		getInner().addCustomField(custom);
		return this;
	}

	public PageObjectBuilder addNamedAssert(String name, String xpath) {
		getInner().addNamedAssert(name, xpath);
		return this;
	}

	public boolean equals(Object obj) {
		return getInner().equals(obj);
	}

	public String toString() {
		return getInner().toString();
	}

	public final PageObjectBuilderSymbolTable getInner() {
		return inner;
	}

	public final void setInner(PageObjectBuilderSymbolTable inner) {
		this.inner = inner;
	}
	
	public final PageObjectBuilderSymbolTable self(){
		return this;
	}

	@Override
	public final void setOuter(DecoratorObject<PageObjectBuilderSymbolTable> value) {
		this.outer = value;
	}

	@Override
	public final PageObjectBuilderSymbolTable getOuter() {
		if(outer==null)
			return this;
		return outer.getOuter();
	}
	
	
}
