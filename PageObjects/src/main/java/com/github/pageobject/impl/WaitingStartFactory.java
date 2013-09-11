package com.github.pageobject.impl;

import com.github.pageobject.IncompletePageObject;
import com.github.pageobject.PageObjectBuilder;

public interface WaitingStartFactory {
	public PageObjectBuilder startBuild(IncompletePageObject pageObject);
}
