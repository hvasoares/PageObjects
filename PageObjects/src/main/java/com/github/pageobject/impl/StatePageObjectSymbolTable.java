package com.github.pageobject.impl;

import com.github.pageobject.Mutability;
import com.github.pageobject.StatePageObject;

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
