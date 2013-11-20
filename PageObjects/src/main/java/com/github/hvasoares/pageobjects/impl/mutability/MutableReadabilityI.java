package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.List;


public interface MutableReadabilityI {

	void addReadProperty(String alias, String xpath);
	String read(String ...args);
	public abstract List<String> readAsList(String ...args);
}
