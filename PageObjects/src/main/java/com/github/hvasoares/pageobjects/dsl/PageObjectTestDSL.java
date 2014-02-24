package com.github.hvasoares.pageobjects.dsl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.runner.PageObjectFactoryAware;
public class PageObjectTestDSL {

	private AbstractFactory factory;

	public AbstractFactory getFactory() {
		return factory;
	}

	public final PageObjectTestDSL click(String alias, String...arguments){
		if(arguments.length==0)
			statePageObject().click(alias);
		else
			mutability().click(addToHead(alias,arguments));
		return this;
	}
	
	public final List<String> readAsList(String ...args){
		return mutability().readAsList(args);
	}

	protected Mutability mutability() {
		return statePageObject().mutability();
	}

	protected StatePageObject statePageObject() {
		return factory.getStateObject();
	}
	
	private String[] addToHead(String head, String[] arguments) {
		String [] result = new String[arguments.length+1];
		result[0]=head;
		for(int i=1; i< result.length;i++)
			result[i] = arguments[i-1];
		return result;
	}

	public final PageObjectTestDSL fill(String alias,String... arguments){
		checkArgument(arguments.length!=0,"You must provide at least one argument beside alias");
		if(arguments.length==1)
			statePageObject().fill(alias,arguments[0]);
		else if(arguments.length>=2){
			mutability().fill(alias,arguments[0],tail(arguments));
		}
		
		return this;
	}
	
	public final String read(String property,String ...arguments){
		if(arguments.length==0)
			return statePageObject().readability().read(property);
		return mutability().read(addToHead(property, arguments));
	}
	
	private String[] tail(String[] arguments) {
		if(arguments.length==0)
			return arguments;
		String[] result = new String[arguments.length-1];
		for(int i=0; i<result.length; i++){
			result[i]=arguments[i+1];
		}
		return result ;
	}

	@PageObjectFactoryAware
	public final void setFactory(AbstractFactory value){
		this.factory = value;
	}

	public WebDriver getWebDriver() {
		return factory.getWebDriver();
	}
	
	
}
