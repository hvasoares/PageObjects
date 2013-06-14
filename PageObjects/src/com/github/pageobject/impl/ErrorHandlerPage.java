package com.github.pageobject.impl;

import com.github.pageobject.PageObject;

public class ErrorHandlerPage implements PageObject {
	private PageObject innerPage;
		
	public ErrorHandlerPage(PageObject innerPage) {
		super();
		this.innerPage = innerPage;
	}

	@Override
	public PageObject fill(String field, String value) {
		try{
			innerPage.fill(field,value);
		}catch(RuntimeException ex){
			throw new RuntimeException(
				"Some problems showed when trying to fill field " +
				"'"+field+ "' with value '"+value+"' at page '"+getName()+"'",
				ex
			);
		}
		return this;
	}

	@Override
	public PageObject click(String alias) {
		try{
			innerPage.click(alias);
		}catch(RuntimeException ex){
			throw new RuntimeException(
					"Some problems showed when trying click button " +
							"'"+alias+"' at page '"+getName()+"'",
				ex
			);
		}
		return this;
	}

	@Override
	public String getName() {
		return innerPage.getName();
	}

}
