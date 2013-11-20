package com.github.hvasoares.pageobjects.impl.el;

import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.FieldFactory;

public class ElFieldImpl implements TestableElField {
	private ElContext elContext;
	
	private FieldFactory fieldFactory;
	private String alias,xpath;
	
	public ElFieldImpl(ElContext elContext, FieldFactory fieldFactory,
			String alias, String xpath) {
		super();
		this.elContext = elContext;
		this.fieldFactory = fieldFactory;
		this.alias = alias;
		this.xpath = xpath;
	}

	@Override
	public void fill(String value) {
		String evalValue = elContext.evaluate(value);
		Field field = fieldFactory.createTextField(alias,elContext.evaluate(xpath));
		field.fill(evalValue);
		elContext.assign(alias,evalValue);
	}
	
	public ElContext getElContext() {
		return elContext;
	}

	@Override
	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}

	public String getAlias() {
		return alias;
	}

	public String getXpath() {
		return xpath;
	}
}
