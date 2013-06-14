package com.github.pageobject.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.pageobject.PageObject;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.runner.PageObjectRepository;

public class StatePageObjectImpl implements StatePageObject{
	private Map<String,PageObject> allStates;
	private String now;
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
		now = value;
	}

	private void addPage(PageObject page) {
		allStates.put(page.getName(),page);
	}

	@Override
	public PageObject fill(String field, String value) {
		allStates.get(now).fill(field,value);
		return this;
	}

	@Override
	public PageObject click(String alias) {
		allStates.get(now).click(alias);
		return this;
	}

	@Override
	public String getName() {
		return "StateMachine";
	}
}
