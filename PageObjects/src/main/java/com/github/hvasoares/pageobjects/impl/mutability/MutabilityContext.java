package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.HashMap;
import java.util.Map.Entry;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.FieldFactory;

import static com.google.common.base.Preconditions.checkNotNull;
public class MutabilityContext implements MutabilityContextI{

	private FieldFactory fieldFactory;
	private HashMap<String, FluidXpathI> xpathDb;
	private HashMap<String, String> transition;
	public MutabilityContext(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
		this.xpathDb = new HashMap<String,FluidXpathI>();
		this.transition = new HashMap<String,String>();
	}

	@Override
	public void add(String alias, FluidXpathI click) {
		this.xpathDb.put(alias,click);
	}

	@Override
	public void add(String alias, FluidXpathI click, String page) {
		add(alias,click);
		transition.put(alias, page);
	}

	@Override
	public FluidXpathI get(String alias) {
		return checkNotNull(xpathDb.get(alias),"The alias '"+alias+"' returned null");
	}

	@Override
	public void click(String alias, String []arguments) {
		createClick(alias, arguments).click();
	}

	private Clickable createClick(String alias, String[] arguments) {
		FluidXpathI xpath = get(alias);
		for(Entry<String, String> e : Utils.toMapSetEntry(arguments)){
			xpath.bind(e.getKey(), e.getValue());
		}
		Clickable click;
		if(transition.containsKey(alias))
			click =fieldFactory.createClickable(alias, xpath.getTransformedXpath(),transition.get(alias));
		else
			click =fieldFactory.createClickable(alias, xpath.getTransformedXpath());
		return click;
	}

	@Override
	public void doubleClick(String alias,String[] args) {
		createClick(alias,args).doubleClick();
	}

}
