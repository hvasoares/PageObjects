package com.github.pageobject.impl.field;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.browser.Browser;

public class ClickableImpl implements Clickable{

	private ClickableParams params;
	private Browser browser;
	private StatePageObject machine;

	public ClickableImpl(ClickableParams params,Browser browser, StatePageObject machine) {
		this.params = params;
		this.browser = browser;
		this.machine = machine;
	}

	@Override
	public String getAlias() {
		return params.getAlias();
	}

	@Override
	public void click() {
		browser.click(params.getXpath());
		if(params.isStateChange()){
			machine.setState(params.getToPageAlias());
		}
	}

}
