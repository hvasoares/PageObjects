package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.StatePageObject;

public interface PageContextI {

	com.github.hvasoares.pageobjects.Mutability createMutability(String contextName, PageObjectBuilder pageObjectBuilderSymbolTable);

	com.github.hvasoares.pageobjects.Mutability get(String pagename, StatePageObject statePageObject);

}
