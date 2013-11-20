package com.github.pageobject.impl.readability;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.Readability;

public abstract class ReadabilityImplementationFactory {
	private static ReadabilityContextImpl context;
	private static ReadabilityBuilder readabilityBuilder;
	private static WebDriver wDriver;

	public static ProxyStatePageObjectAdapter createReadabilityStatePageObject(){
		return createReadabilityContextImpl();
	}
	
	public static ProxyPageObjectBuilderAdapter createReadabilityBuilder(WebDriver webDriver){
		if(context==null)
			createReadabilityStatePageObject();
		if(readabilityBuilder!=null)
			return readabilityBuilder;
		readabilityBuilder = new ReadabilityBuilder(wDriver = webDriver);
		readabilityBuilder.setReadabilitCtx(createReadabilityContextImpl());
		return readabilityBuilder;
	}
	
	private static ReadabilityContextImpl createReadabilityContextImpl(){
		if(context!=null)
			return context;
		return context = new ReadabilityContextImpl();
	}

	public static ReadabilityFactory createDetachedReadabilityFactory() {
		return new ReadabilityFactory() {
			@Override
			public Readability create() {
				return new ReadabilityImpl(wDriver);
			}
		};
	}
}
