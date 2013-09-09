package com.github.pageobject.impl;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.PageObjectBuilder;

public interface WaitingStartFactory {
	public PageObjectBuilder startBuild(AssertivePageObject pageObject);
}
