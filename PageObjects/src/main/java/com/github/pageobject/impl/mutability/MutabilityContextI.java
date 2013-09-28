package com.github.pageobject.impl.mutability;

public interface MutabilityContextI {

	void add(String alias, FluidXpathI click);

	void add(String alias, FluidXpathI click, String page);

	FluidXpathI get(String string);

	public abstract void click(String alias, String []arguments);

	void doubleClick(String alias, String[] args);

}
