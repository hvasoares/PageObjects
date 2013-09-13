package com.github.pageobject.impl;

import javax.swing.JOptionPane;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.proxy.DecoratorObject;

public class ProxyStatePageObjectAdapter extends StatePageObjectSymbolTable implements DecoratorObject<StatePageObjectSymbolTable> {
	private StatePageObjectSymbolTable inner;

	public StatePageObject checkAssertion(String namedAssertion) {
		return getInner().checkAssertion(namedAssertion);
	}

	public StatePageObject click(String alias) {
		return getInner().click(alias);
	}

	public boolean equals(Object obj) {
		return getInner().equals(obj);
	}

	public StatePageObject fill(String field, String value) {
		return getInner().fill(field, value);
	}

	public String getName() {
		return getInner().getName();
	}

	public int hashCode() {
		return getInner().hashCode();
	}

	public void setState(String stateName) {
		getInner().setState(stateName);
	}

	public String toString() {
		return getInner().toString();
	}

	public StatePageObjectSymbolTable getInner() {
		return inner;
	}

	public void setInner(StatePageObjectSymbolTable inner) {
		this.inner = inner;
	}
	
	public StatePageObjectSymbolTable self(){
		return this;
	}
}
