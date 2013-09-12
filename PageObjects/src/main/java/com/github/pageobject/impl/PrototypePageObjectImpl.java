package com.github.pageobject.impl;

import com.github.pageobject.PageObject;
import com.github.pageobject.PrototypePageObject;
import com.github.pageobject.proxy.ProxyPageObjectAdapter;

public class PrototypePageObjectImpl extends PageObjectSymbolTable{

	private PageObject clone;

	public PrototypePageObjectImpl(PageObject clone) {
		this.clone = clone;
	}
	@Override
	public PageObject fill(String field, String value) {
		try{
			clone.fill(field,value);
		}catch(RuntimeException ex){
			getInner().fill(field,value);
		}
		return this;
	}
	@Override
	public PageObject click(String button) {
		try{
			clone.click(button);
		}catch(RuntimeException ex){
			getInner().click(button);
		}
		return this;
	}
	@Override
	public String getName() {
		return getInner().getName();
	}
}
