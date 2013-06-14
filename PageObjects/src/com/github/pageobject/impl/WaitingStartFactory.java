package com.github.pageobject.impl;

import com.github.pageobject.ImcompletePageObject;
import com.github.pageobject.PageObjectBuilder;

public interface WaitingStartFactory {
	public PageObjectBuilder startBuild(ImcompletePageObject pageObject);
}
