package com.github.hvasoares.pageobjects.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

public abstract class RepositoryContainer implements PageObjectRepository{

	private List<PageObject> result;
	private List<PageObjectRepository> listOfRepositories;

	@Override
	final public List<PageObject> getPages() {
		result = new ArrayList<PageObject>();
		for(PageObjectRepository e : getRepositoriesAndSave())
			result.addAll(e.getPages());
		return result;
	}

	@Override
	final public void setBuilderFactory(PageObjectBuilderFactory factory) {
		for(PageObjectRepository e : getRepositoriesAndSave())
			e.setBuilderFactory(factory);
	}
	
	abstract public <T extends PageObjectRepository> List<T> getRepositories();
	
	
	@SuppressWarnings("unchecked")
	private <T extends PageObjectRepository> List<T> getRepositoriesAndSave(){
		if(listOfRepositories!=null)
			return (List<T>) listOfRepositories;
		listOfRepositories = getRepositories();
		return (List<T>) listOfRepositories;
	}

}