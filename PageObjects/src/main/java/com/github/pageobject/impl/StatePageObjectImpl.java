package com.github.pageobject.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.runner.PageObjectRepository;

public class StatePageObjectImpl implements StatePageObject{
	private Map<String,AssertivePageObject> allStates;
	private AssertivePageObject now;
	private PageObjectRepository repo;
	private boolean setted;
	
	public StatePageObjectImpl(PageObjectRepository repository) {
		allStates = new HashMap<String, AssertivePageObject>();
		repo = repository;
		setted = false;
	}
	private void start(){
		if(setted)
			return;
		for(AssertivePageObject p : repo.getPages())
			addPage(p);
		setted = true;
	}
	
	@Override
	public void setState(String value) {
		start();
		now = allStates.get(value);
	}

	private void addPage(AssertivePageObject page) {
		allStates.put(page.getName(),page);
	}

	@Override
	public StatePageObjectImpl fill(String field, String value) {
		now.fill(field,value);
		return this;
	}

	@Override
	public StatePageObjectImpl click(String alias) {
		now.click(alias);
		return this;
	}
	
	public StatePageObjectImpl assertXpath(String xpath){
		now.pageAssertion(xpath);
		return this;
	}

	
	public StatePageObjectImpl checkAssertion(String namedAssertion){
		now.checkAssertion(namedAssertion);
		return this;
	}

	@Override
	public String getName() {
		return "StateMachine";
	}
}
