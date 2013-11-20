package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface BuildTimeI {

	PageObjectBuilder getPageObjectBuilder();

	void add(String alias, String xpathWithPlaceHolders, String toPage);
	void add(String alias, String xpathWithPlaceHolders);

	void extendsClickable(String ...args);

}
