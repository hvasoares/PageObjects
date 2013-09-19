package com.github.pageobject.impl.mutability;

import com.github.pageobject.StatePageObject;

public interface ExecutionTimeI {

	StatePageObject getStatePageObject();

	void click(String ...args);

	void setStateObject(StatePageObject value);

}
