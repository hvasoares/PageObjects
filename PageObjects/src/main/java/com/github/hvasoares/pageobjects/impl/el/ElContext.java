package com.github.hvasoares.pageobjects.impl.el;

import com.github.hvasoares.pageobjects.StatePageObject;

public interface ElContext extends StatePageObject{

	void assign(String var, String value);

	String evaluate(String expression);

}
