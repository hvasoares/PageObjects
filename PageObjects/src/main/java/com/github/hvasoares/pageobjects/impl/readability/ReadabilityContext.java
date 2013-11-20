package com.github.hvasoares.pageobjects.impl.readability;

import com.github.hvasoares.pageobjects.impl.Readability;

public interface ReadabilityContext {

	void add(String pageName, Readability current);
	public Readability get(String pageName);
}
