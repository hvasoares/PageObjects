package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public interface ClickableI extends MultiArgsClickable {

	public abstract ExecutionTimeI getExecutionTime();

	public abstract PageObjectBuilder addClickable(String alias, String xpath);

	public abstract PageObjectBuilder addClickable(String alias, String xpath, String pageToTransitioning);
	
	public PageObjectBuilder getPageObjectBuilder();

}
