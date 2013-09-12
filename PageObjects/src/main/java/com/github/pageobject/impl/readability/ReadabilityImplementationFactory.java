package com.github.pageobject.impl.readability;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;

public abstract class ReadabilityImplementationFactory {
	private static ReadabilityContextImpl context;
	private static ReadabilityBuilder readabilityBuilder;

	public static ProxyStatePageObjectAdapter createReadabilityStatePageObject(){
		if(context!=null)
			return context;
		return context = new ReadabilityContextImpl();
	}
	
	public static ProxyPageObjectBuilderAdapter createReadabilityBuilder(WebDriver webDriver){
		if(readabilityBuilder!=null)
			return readabilityBuilder;
		readabilityBuilder = new ReadabilityBuilder(webDriver);
		readabilityBuilder.setReadabilitCtx(context= new ReadabilityContextImpl());
		return readabilityBuilder;
	}
}
