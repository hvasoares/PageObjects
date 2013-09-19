package com.github.pageobject.impl.mutability;

public interface FluidXpathI {

	public abstract String getTransformedXpath();

	public abstract void bind(String placeHolderName, String value);

	public abstract String getXpath();

}
