package com.github.pageobject.impl.readability;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;

public abstract class ReadabilityImplementationFactory {
	private static ReadabilityContextImpl context;
	private static ReadabilityBuilder readabilityBuilder;

	public static ProxyStatePageObjectAdapter createReadabilityStatePageObject(){
		return createReadabilityContextImpl();
	}
	
	public static ProxyPageObjectBuilderAdapter createReadabilityBuilder(WebDriver webDriver){
		if(context==null)
			createReadabilityStatePageObject();
		if(readabilityBuilder!=null)
			return readabilityBuilder;
		readabilityBuilder = new ReadabilityBuilder(webDriver);
		readabilityBuilder.setReadabilitCtx(createReadabilityContextImpl());
		return readabilityBuilder;
	}
	
	private static ReadabilityContextImpl createReadabilityContextImpl(){
		if(context!=null)
			return context;
		return context = new ReadabilityContextImpl();
	}
}
