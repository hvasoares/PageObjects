package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public class Mutability implements com.github.pageobject.Mutability{


	private ExecutionTimeI runtime;
	private BuildTimeI build;

	public Mutability(BuildTimeI buildTimeMutability,ExecutionTimeI runtimeMutability) {
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

	public ExecutionTimeI getExecutionTime() {
		return runtime;
	}

}
