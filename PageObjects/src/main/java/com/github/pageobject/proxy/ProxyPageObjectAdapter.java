package com.github.pageobject.proxy;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObject;
import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.Field;

public abstract class ProxyPageObjectAdapter implements ProxyPageObject{

	private IncompletePageObject inner;
	
	protected final IncompletePageObject getInner(){
		return this.inner;
	}
	
	@Override
	public final void setInnerObject(IncompletePageObject value) {
		this.inner = value;
	}

	public void pageAssertion(String xpath) {
		inner.pageAssertion(xpath);
	}



	public PageObject fill(String field, String value) {
		return inner.fill(field, value);
	}



	public void addNamedAssertion(String name, String xpath) {
		inner.addNamedAssertion(name, xpath);
	}



	public PageObject click(String string) {
		return inner.click(string);
	}



	public void checkAssertion(String namedAssertion) {
		inner.checkAssertion(namedAssertion);
	}



	public String getName() {
		return inner.getName();
	}



	public void addField(Field newField) {
		inner.addField(newField);
	}



	public void addClickable(Clickable click) {
		inner.addClickable(click);
	}



	public void setName(String value) {
		inner.setName(value);
	}

}
