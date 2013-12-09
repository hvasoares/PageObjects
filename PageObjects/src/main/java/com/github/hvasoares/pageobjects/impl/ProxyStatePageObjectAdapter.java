package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.proxy.DecoratorObject;

public class ProxyStatePageObjectAdapter extends StatePageObjectSymbolTable implements DecoratorObject<StatePageObject> {
	private StatePageObject inner;
	private DecoratorObject<StatePageObject> outer;

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

	public StatePageObject getInner() {
		return inner;
	}
	
	public StatePageObjectSymbolTable self(){
		return this;
	}

	@Override
	public final void setInner(StatePageObject value) {
		this.inner = value;
	}

	@Override
	public final void setOuter(DecoratorObject<StatePageObject> value) {
		this.outer = value;
	}

	@Override
	public final StatePageObject getOuter() {
		if(outer ==null)
			return this;
		return outer.getOuter();
	}

	@Override
	public StatePageObject doubleClick(String alias) {
		return getInner().doubleClick(alias);
	}
}
