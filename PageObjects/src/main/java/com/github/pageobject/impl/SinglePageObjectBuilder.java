package com.github.pageobject.impl;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;

public interface SinglePageObjectBuilder extends PageObjectBuilder{

	public abstract PageObjectBuilder setName(String value);

	public abstract PageObject get();
	
}
