package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.IncompletePageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface WaitingStartFactory {
	public PageObjectBuilder startBuild(IncompletePageObject pageObject);
}
