package com.github.pageobject.proxy;

import com.github.pageobject.IncompletePageObject;

public interface ProxyPageObject extends IncompletePageObject{
	public void setInnerObject(IncompletePageObject value);
}
