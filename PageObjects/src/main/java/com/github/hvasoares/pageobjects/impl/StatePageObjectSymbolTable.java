package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.StatePageObject;

public abstract class StatePageObjectSymbolTable implements StatePageObject {

	private static Readability readability;
	private static Mutability mutability;

	@Override
	public final Readability readability() {
		return readability;
	}
	
	protected static final void setReadability(Readability value){
		readability = value;
	}

	@Override
	public Mutability mutability() {
		return mutability;
	} 
	
	protected void setMutability(Mutability value){
		mutability = value;
	}
	
}
