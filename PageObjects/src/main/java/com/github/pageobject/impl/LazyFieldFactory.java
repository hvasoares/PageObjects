package com.github.pageobject.impl;

import com.github.pageobject.impl.field.CustomField;

public class LazyFieldFactory implements FieldFactory{
	private FieldFactory inner;
	private ActualFieldFactoryGetter strategy;

	public LazyFieldFactory(ActualFieldFactoryGetter getter) {
		this.strategy = getter;
	}

	public Field createTextField(String string, String string2) {
		return getInner().createTextField(string, string2);
	}

	public Clickable createClickable(String string, String xpath,
			String toPageAlias) {
		return getInner().createClickable(string, xpath, toPageAlias);
	}

	public Clickable createClickable(String alias, String xpath) {
		return getInner().createClickable(alias, xpath);
	}

	public Field createFileField(String value, String xpath) {
		return getInner().createFileField(value, xpath);
	}

	public Field createCustomField(CustomField custom) {
		return getInner().createCustomField(custom);
	}

	FieldFactory getInner() {
		inner = strategy.getFieldFactory();
		return inner;
	}
}
