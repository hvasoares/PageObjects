package com.github.hvasoares.pageobjects.runner;

import org.openqa.selenium.WebDriver;

import lombok.Delegate;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.DefaultFactory;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.browser.Browser;

public class LazyAbstractFactory implements AbstractFactory {

	private DefaultFactory inner;
	
	public PageObjectBuilder createPageObjectBuilder() {
		return inner.createPageObjectBuilder();
	}

	public SerialPageObjectBuilderI createSerialPageObjectBuilder() {
		return inner.createSerialPageObjectBuilder();
	}

	public boolean equals(Object obj) {
		return inner.equals(obj);
	}

	public StatePageObject getStateObject() {
		return inner.getStateObject();
	}

	public Browser getBrowser() {
		return inner.getBrowser();
	}

	public WebDriver getWebDriver() {
		return inner.getWebDriver();
	}

	public FieldFactory getFieldFactory() {
		return inner.getFieldFactory();
	}

	public int hashCode() {
		return inner.hashCode();
	}

	public String toString() {
		return inner.toString();
	}

	public void setRepository(PageObjectRepository repository){
		this.inner = new DefaultFactory(repository);
	}

}
