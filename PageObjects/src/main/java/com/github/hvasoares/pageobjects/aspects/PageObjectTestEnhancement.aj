package com.github.hvasoares.pageobjects.aspects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.runner.PageObjectFactoryAware;

public aspect PageObjectTestEnhancement {
	private AbstractFactory EnhancedPageObjectTest.factory = null;
	private AbstractFactory factory;

	public void EnhancedPageObjectTest.setInnerFactory(AbstractFactory value){
		this.factory = value;
	}
	
	public StatePageObject EnhancedPageObjectTest.getStatePageObject(){
		return getFactory().getStateObject();
	}
	public Browser EnhancedPageObjectTest.getBrowser(){
		return getFactory().getBrowser();
	}
	public AbstractFactory EnhancedPageObjectTest.getFactory(){
		return this.factory;
	}

	public void setFactory(AbstractFactory value){
		this.factory= value;
	}
	
	public StatePageObject EnhancedPageObjectTest.click(String alias){
		return this.getStatePageObject().click(alias);
	}
	
	public StatePageObject EnhancedPageObjectTest.fill(String alias,String value){
		return this.getStatePageObject().fill(alias,value);
	}

	public StatePageObject EnhancedPageObjectTest.checkAssertion(String namedAssertion){
		return getStatePageObject().checkAssertion(namedAssertion);
	}
	
	public StatePageObject EnhancedPageObjectTest.doubleClick(String alias){
		return getStatePageObject().doubleClick(alias);
	}
	
	public Readability EnhancedPageObjectTest.readability(){
		return getStatePageObject().readability();
	}

	public Mutability EnhancedPageObjectTest.mutability(){
		return getStatePageObject().mutability();
	}
	
	public WebDriver EnhancedPageObjectTest.getWebDriver(){
		return getFactory().getWebDriver();
	}
}
