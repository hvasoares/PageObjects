package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.Arrays;

import com.github.hvasoares.pageobjects.StatePageObject;

import static com.google.common.base.Preconditions.checkArgument;
public class ExecutionTime implements ExecutionTimeI{

	private StatePageObject stateObject;
	private MutabilityContextI context;

	public ExecutionTime(MutabilityContextI mCtx) {
		context = mCtx;
	}

	@Override
	public StatePageObject getStatePageObject() {
		return stateObject;
	}

	@Override
	public void click(String... args) {
		checkArgument(args.length >=2, "The arguments array should be: alias (placeHolder value | singleValue )+");
		context.click(args[0], Arrays.copyOfRange(args, 1, args.length));
	}

	@Override
	public void setStateObject(StatePageObject value) {
		this.stateObject = value;
	}

	@Override
	public void doubleClick(String ... args) {
		context.doubleClick(args[0], Arrays.copyOfRange(args, 1, args.length));
	}

}
