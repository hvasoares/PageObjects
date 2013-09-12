package com.github.pageobject.impl.readability;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;

public class ReadabilityBuilder extends ProxyPageObjectBuilderAdapter{

	private ReadabilityImpl current;
	private ReadabilityContext readabilitCtx;
	private WebDriver driver;

	public ReadabilityBuilder(WebDriver webDriver) {
		this.driver = webDriver;
	}

	@Override
	public PageObjectBuilder addTextField(String fieldName, String xpath) {
		current.setProperty(fieldName, xpath);
		return getInner().addTextField(fieldName, xpath);
	}

	@Override
	public PageObjectBuilder setName(String value) {
		current = new ReadabilityImpl(driver);
		readabilitCtx.add(value,current);
		setReadability(current);
		return getInner().setName(value);
	}
	
	void setReadabilitCtx(ReadabilityContext readabilitCtx) {
		this.readabilitCtx = readabilitCtx;
	}

	ReadabilityImpl getCurrent() {
		return current;
	}
}
