package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.Assertiveness;


public class PageObjectImpl extends PageObjectSymbolTable{
	
	private FieldContainer fieldCont;
	private ClickableContainer clickCont;
	private String name;	
	private Assertiveness assertiveness;

	public PageObjectImpl(ClickableContainer clickCont, FieldContainer fieldCont, Assertiveness assertiveness) {
		this.clickCont = clickCont;
		this.fieldCont = fieldCont;
		this.assertiveness = assertiveness;
	}
	
	public void pageAssertion(String xpath) {
		assertiveness.pageAssertion(xpath);
	}

	public void addNamedAssertion(String name, String xpath) {
		assertiveness.addNamedAssertion(name, xpath);
	}

	public void checkAssertion(String namedAssertion) {
		assertiveness.checkAssertion(namedAssertion);
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

	@Override
	public PageObject doubleClick(String alias) {
		clickCont.doubleClick(alias);
		return this;
	}
}
