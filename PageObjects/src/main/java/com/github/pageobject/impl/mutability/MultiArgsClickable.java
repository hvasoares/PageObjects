package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public interface MultiArgsClickable {
	public abstract StatePageObject click(String ...args);

	public abstract PageObjectBuilder extendsClickable(String ...args);
}