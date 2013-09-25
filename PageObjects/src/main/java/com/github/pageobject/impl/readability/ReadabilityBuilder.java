package com.github.pageobject.impl.readability;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.Readability;

public class ReadabilityBuilder extends ProxyPageObjectBuilderAdapter{

	private Readability current;
	private ReadabilityContext readabilitCtx;
	private WebDriver driver;

	public ReadabilityBuilder(WebDriver webDriver) {
		this.driver = webDriver;
	}

	@Override
	public PageObjectBuilder addTextField(String fieldName, String xpath) {
		current.setProperty(fieldName, xpath);
		getInner().addTextField(fieldName, xpath);
		return this;
	}

	@Override
	public PageObjectBuilder setName(String value) {
		current = readabilitCtx.get(value); 
		if(current==null){
			current = new ReadabilityImpl(driver);
			readabilitCtx.add(value,current);
		}
		
		setReadability(current);
		return super.setName(value);
	}
	
	void setReadabilitCtx(ReadabilityContext readabilitCtx) {
		this.readabilitCtx = readabilitCtx;
	}

	Readability getCurrent() {
		return current;
	}
}
