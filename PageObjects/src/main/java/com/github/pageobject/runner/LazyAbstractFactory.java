package com.github.pageobject.runner;

import org.openqa.selenium.WebDriver;

import lombok.Delegate;

import com.github.pageobject.AbstractFactory;
import com.github.pageobject.DefaultFactory;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.SerialPageObjectBuilderI;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.browser.Browser;

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
