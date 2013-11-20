package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.IncompletePageObject;
import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.impl.field.CustomField;

public class PageObjectFactoryImpl extends PageObjectBuilderSymbolTable{

	private FieldFactory fieldFactory;
	private IncompletePageObject object;

	public PageObjectFactoryImpl(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}

	@Override
	public PageObjectBuilder addClickable(String aliase, String xpath) {
		object.addClickable(fieldFactory.createClickable(aliase,xpath));
		return this;
	}

	@Override
	public PageObjectBuilder addTextField(String aliase, String xpath) {
		this.object.addField(fieldFactory.createTextField(aliase,xpath));
		return this;
	}

	@Override
	public PageObject get() {
		return object;
	}

	public PageObjectBuilder startBuild(IncompletePageObject newOne ) {
		this.object = newOne;
		return this;
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath, String toPage) {
		this.object.addClickable(fieldFactory.createClickable(alias,xpath,toPage));
		return this;
	}

	@Override
	public PageObjectBuilder setName(String value) {
		this.object.setName(value);
		return this;
	}

	@Override
	public PageObjectBuilder addCustomField(CustomField custom) {
		this.object.addField(fieldFactory.createCustomField(custom));
		return this;
	}

	@Override
	public PageObjectBuilder addFileField(String value, String xpath) {
		this.object.addField(fieldFactory.createFileField(value,xpath));
		return this;
	}
	
	@Override
	public PageObjectBuilder addNamedAssert(String name, String xpath){
		object.addNamedAssertion(name, xpath);
		return this;
	}

}
