package com.github.pageobject.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.PageObjectBuilderFactory;

public class SerialPageObjectBuilder implements PageObjectBuilder{
	private List<PageObject> all;
	private PageObjectBuilderFactory factory;
	private PageObjectBuilder current;
	public SerialPageObjectBuilder(PageObjectBuilderFactory factory){
		this.all = new ArrayList<PageObject>();
		this.factory = factory;
	}
	public SerialPageObjectBuilder newPage(String name){
		if(current!=null)
			all.add(current.get());
		current = factory.createPageObjectBuilder();
		current.setName(name);
		return this;
	}
	@Override
	public PageObjectBuilder addClickable(String string, String string2) {
		current.addClickable(string,string2);
		return this;
	}

	@Override
	public PageObjectBuilder addTextField(String string, String string2) {
		current.addTextField(string,string2);
		return this;
	}

	@Override
	public PageObject get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath,
			String toPage) {
		current.addClickable(alias,xpath,toPage);
		return this;
	}

	@Override
	public PageObjectBuilder setName(String value) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<PageObject> getAll() {
		if(current!=null){
			all.add(current.get());
			current=null;
		}
		return all;
	}
	
}
