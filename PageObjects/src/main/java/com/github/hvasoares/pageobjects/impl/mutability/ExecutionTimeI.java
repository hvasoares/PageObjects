package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.StatePageObject;

public interface ExecutionTimeI {

	StatePageObject getStatePageObject();

	void click(String ...args);

	void setStateObject(StatePageObject value);

	void doubleClick(String... args);

}
