package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface SinglePageObjectBuilder extends PageObjectBuilder{

	public abstract PageObjectBuilder setName(String value);

	public abstract PageObject get();
	
}
