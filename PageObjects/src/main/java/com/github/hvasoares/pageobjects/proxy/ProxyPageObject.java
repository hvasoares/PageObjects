package com.github.hvasoares.pageobjects.proxy;

import com.github.hvasoares.pageobjects.IncompletePageObject;

public interface ProxyPageObject extends IncompletePageObject{
	public void setInnerObject(IncompletePageObject value);
}
