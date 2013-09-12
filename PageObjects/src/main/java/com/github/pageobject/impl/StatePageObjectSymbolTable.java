package com.github.pageobject.impl;

import com.github.pageobject.StatePageObject;

public abstract class StatePageObjectSymbolTable implements StatePageObject {

	private static Readability readability;

	@Override
	public final Readability readability() {
		return readability;
	}
	
	protected final void setReadability(Readability value){
		this.readability = value;
	} 
}
