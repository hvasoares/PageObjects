package com.github.pageobject.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.PageObject;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.runner.PageObjectRepository;
public class StatePageObjectImpl extends StatePageObjectSymbolTable{
	private Map<String,PageObject> allStates;
	private PageObject now;
	private PageObjectRepository repo;
	private boolean setted;
	
	public StatePageObjectImpl(PageObjectRepository repository) {
		allStates = new HashMap<String, PageObject>();
		repo = repository;
		setted = false;
	}
	private void start(){
		if(setted)
			return;
		for(PageObject p : repo.getPages())
			addPage(p);
		setted = true;
	}
	
	@Override
	public void setState(String value) {
		start();
		now = allStates.get(value);
		checkNotNull(now,"the page '" + value + "' isn't setted");
	}

	private void addPage(PageObject page) {
		allStates.put(page.getName(),page);
	}

	@Override
	public StatePageObject fill(String field, String value) {
		now.fill(field,value);
		return this;
	}

	@Override
	public StatePageObject click(String alias) {
		now.click(alias);
		return this;
	}
	
	public StatePageObject assertXpath(String xpath){
		now.pageAssertion(xpath);
		return this;
	}

	@Override
	public StatePageObject checkAssertion(String namedAssertion){
		now.checkAssertion(namedAssertion);
		return this;
	}

	@Override
	public String getName() {
		return "StateMachine";
	}
}
