package com.github.pageobject.impl;

import com.github.pageobject.ImcompletePageObject;
import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;

public class PageObjectBuilderImpl implements SinglePageObjectBuilder,WaitingStartFactory {

	private FieldFactory fieldFactory;
	private ImcompletePageObject object;

	public PageObjectBuilderImpl(FieldFactory fieldFactory) {
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

	public SinglePageObjectBuilder startBuild(ImcompletePageObject newOne ) {
		this.object = newOne;
		return this;
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath, String toPage) {
		this.object.addClickable(fieldFactory.createClickable(alias,xpath,toPage));
		return this;
	}

	@Override
	public SinglePageObjectBuilder setName(String value) {
		this.object.setName(value);
		return this;
	}

}
