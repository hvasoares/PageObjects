package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.StatePageObject;

public interface MultiArgsClickable {
	public abstract StatePageObject click(String ...args);

	public abstract PageObjectBuilder extendsClickable(String ...args);
	
	public abstract StatePageObject doubleClick(String... args);
}