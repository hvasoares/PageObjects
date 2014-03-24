package com.github.hvasoares.pageobjects;

public interface AutomataFieldFiller {

	public abstract PageObjectBuilder addFieldXpathPair(String alias, String xpath);

	public abstract PageObjectBuilder addFieldXpathPairs(String... args);

	public abstract void setBuilder(PageObjectBuilder builder);

}