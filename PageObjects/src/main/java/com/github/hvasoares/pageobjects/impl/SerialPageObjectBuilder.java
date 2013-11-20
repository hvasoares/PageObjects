package com.github.hvasoares.pageobjects.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;

public class SerialPageObjectBuilder implements SerialPageObjectBuilderI {
	private List<PageObject> all;
	private PageObjectBuilderFactory factory;
	private PageObjectBuilder current;
	public SerialPageObjectBuilder(PageObjectBuilderFactory factory){
		this.all = new ArrayList<PageObject>();
		this.factory = factory;
	}
	public PageObjectBuilder newPage(String name){
		if(current!=null){
			all.add(current.get());
		}
		current = factory.createPageObjectBuilder();
		current.setName(name);
		return current;
	}

	public List<PageObject> getAll() {
		if(current!=null){
			all.add(current.get());
			current=null;
		}
		return all;
	}

	
}
