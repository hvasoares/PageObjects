package com.github.pageobject.impl;

import java.util.List;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.SerialPageObjectBuilder;

public class CloneSerialPageObjectBuilderImpl implements SerialPageObjectBuilder{
	private SerialPageObjectBuilder serialBuilder;
	private SinglePageObjectBuilder singleBuilder;
	
	public CloneSerialPageObjectBuilderImpl(SerialPageObjectBuilder serialBuilder,
			SinglePageObjectBuilder singleBuilder) {
		super();
		this.serialBuilder = serialBuilder;
		this.singleBuilder = singleBuilder;
	}

	public PageObjectBuilder addClickable(String string, String string2) {
		return serialBuilder.addClickable(string, string2);
	}

	public PageObjectBuilder addTextField(String string, String string2) {
		return serialBuilder.addTextField(string, string2);
	}

	public PageObjectBuilder addClickable(String alias, String xpath,
			String toPage) {
		return serialBuilder.addClickable(alias, xpath, toPage);
	}

	public SerialPageObjectBuilder newPage(String name) {
		return serialBuilder.newPage(name);
	}

	public List<PageObject> getAll() {
		return serialBuilder.getAll();
	}
}
