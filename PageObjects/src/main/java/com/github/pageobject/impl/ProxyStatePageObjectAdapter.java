package com.github.pageobject.impl;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.proxy.DecoratorObject;

public class ProxyStatePageObjectAdapter extends StatePageObjectSymbolTable implements DecoratorObject<StatePageObjectSymbolTable> {
	StatePageObjectSymbolTable inner;

	public StatePageObject checkAssertion(String namedAssertion) {
		return inner.checkAssertion(namedAssertion);
	}

	public StatePageObject click(String alias) {
		return inner.click(alias);
	}

	public boolean equals(Object obj) {
		return inner.equals(obj);
	}

	public StatePageObject fill(String field, String value) {
		return inner.fill(field, value);
	}

	public String getName() {
		return inner.getName();
	}

	public int hashCode() {
		return inner.hashCode();
	}

	public void setState(String stateName) {
		inner.setState(stateName);
	}

	public String toString() {
		return inner.toString();
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
