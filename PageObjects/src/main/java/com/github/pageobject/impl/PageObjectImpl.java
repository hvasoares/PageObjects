package com.github.pageobject.impl;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObject;


public class PageObjectImpl implements IncompletePageObject{
	
	private FieldContainer fieldCont;
	private ClickableContainer clickCont;
	private String name;

	public PageObjectImpl(ClickableContainer clickCont, FieldContainer fieldCont) {
		this.clickCont = clickCont;
		this.fieldCont = fieldCont;
	}

	@Override
	public PageObject fill(String field, String value) {
		this.fieldCont.fill(field,value);
		return this;
	}

	@Override
	public PageObject click(String alias) {
		this.clickCont.click(alias);
		return this;
	}

	@Override
	public void addField(Field newField) {
		fieldCont.add(newField);
	}

	@Override
	public void addClickable(Clickable click) {
		clickCont.add(click);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
