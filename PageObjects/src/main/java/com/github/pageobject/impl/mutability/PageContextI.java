package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public interface PageContextI {

	com.github.pageobject.Mutability createMutability(String contextName, PageObjectBuilder pageObjectBuilderSymbolTable);

	com.github.pageobject.Mutability get(String pagename, StatePageObject statePageObject);

}
