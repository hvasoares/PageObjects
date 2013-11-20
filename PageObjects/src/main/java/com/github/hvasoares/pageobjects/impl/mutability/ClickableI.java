package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.StatePageObject;

public interface ClickableI extends MultiArgsClickable {

	public abstract ExecutionTimeI getExecutionTime();

	public abstract PageObjectBuilder addClickable(String alias, String xpath);

	public abstract PageObjectBuilder addClickable(String alias, String xpath, String pageToTransitioning);
	
	public PageObjectBuilder getPageObjectBuilder();

	public abstract StatePageObject getStatePageObject();

}
