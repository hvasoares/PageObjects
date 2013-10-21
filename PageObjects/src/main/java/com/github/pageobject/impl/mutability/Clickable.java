package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public class Clickable implements ClickableI{

	private ExecutionTimeI runtime;
	private BuildTimeI build;

	public Clickable(BuildTimeI buildTimeMutability,ExecutionTimeI runtimeMutability) {
		this.runtime = runtimeMutability;
		this.build = buildTimeMutability;
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath,String pageToTransitioning) {
		build.add(alias, xpath,pageToTransitioning);
		return build.getPageObjectBuilder();
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath) {
		build.add(alias, xpath);
		return build.getPageObjectBuilder();
	}


	@Override
	public PageObjectBuilder extendsClickable(String... args) {
		build.extendsClickable(args);
		return build.getPageObjectBuilder();
	}

	@Override
	public StatePageObject click(String... args) {
		runtime.click(args);
		return runtime.getStatePageObject();
	}

	@Override
	public ExecutionTimeI getExecutionTime() {
		return runtime;
	}

	@Override
	public PageObjectBuilder getPageObjectBuilder() {
		return build.getPageObjectBuilder();
	}

	@Override
	public StatePageObject doubleClick(String... args) {
		runtime.doubleClick(args);
		return runtime.getStatePageObject();
	}

	public StatePageObject getStatePageObject() {
		return runtime.getStatePageObject();
	}
	

}
