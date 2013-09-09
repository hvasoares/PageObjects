package com.github.pageobject.impl;

import com.github.pageobject.PageObject;
import com.github.pageobject.PrototypePageObject;

public class PrototypePageObjectImpl implements PrototypePageObject{

	private PageObject clone;
	private PageObject innerObject;

	public PrototypePageObjectImpl(PageObject pageObject1, PageObject clone) {
		this.innerObject = pageObject1;
		this.clone = clone;
	}
	@Override
	public PageObject fill(String field, String value) {
		try{
			clone.fill(field,value);
		}catch(RuntimeException ex){
			innerObject.fill(field,value);
		}
		return this;
	}
	@Override
	public PageObject click(String button) {
		try{
			clone.click(button);
		}catch(RuntimeException ex){
			innerObject.click(button);
		}
		return this;
	}
	@Override
	public String getName() {
		return innerObject.getName();
	}
}
