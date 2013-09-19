package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;

public interface BuildTimeI {

	PageObjectBuilder getPageObjectBuilder();

	void add(String alias, String xpathWithPlaceHolders, String toPage);
	void add(String alias, String xpathWithPlaceHolders);

	void extendsClickable(String ...args);

}
