package com.github.hvasoares.pageobjects.impl.field;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.browser.Browser;

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
		tryChangeMachineState();
	}

	private void tryChangeMachineState() {
		if(params.isStateChange()){
			machine.setState(params.getToPageAlias());
		}
	}
	
	@Override
	public void doubleClick(){
		browser.click(params.getXpath());
		browser.click(params.getXpath());
		tryChangeMachineState();
	}

}
