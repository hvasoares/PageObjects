package com.github.pageobject.impl;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.PageObjectBuilder;

public interface SinglePageObjectBuilder extends PageObjectBuilder{

	public abstract PageObjectBuilder setName(String value);

	public abstract AssertivePageObject get();
	
}
