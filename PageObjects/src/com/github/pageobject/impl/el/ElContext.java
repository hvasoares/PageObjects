package com.github.pageobject.impl.el;

import com.github.pageobject.StatePageObject;

public interface ElContext extends StatePageObject{

	void assign(String var, String value);

	String evaluate(String expression);

}
