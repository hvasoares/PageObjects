package com.github.hvasoares.pageobjects.runner;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.RepositoryAwareFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.WebDriverHolder;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.webdriver.WebDriverFactory;

public class SingletonWebDriverAwareFactory implements RepositoryAwareFactory {

	private RepositoryAwareFactory innerFactory;
	private WebDriver webDriver;
	private WebDriverFactory driverFactory;

	public SingletonWebDriverAwareFactory(Class<?> clazz,RepositoryAwareFactory innerFactory) {
		this.innerFactory = innerFactory;
		initializeWebDriverFactory(clazz);
	}

	private void initializeWebDriverFactory(Class<?> class1) {
		ObjectConstructor objectConstructor = new ObjectConstructor();
		PageObjectTest pobjectTest = class1.getAnnotation(PageObjectTest.class);
		if(pobjectTest.driverFactory()==null){
			return;
		}
		driverFactory = objectConstructor.construct(pobjectTest.driverFactory());
		getWebDriver();
	}
	
	public WebDriver getWebDriver() {
		if(webDriver!=null)
			return webDriver;
		webDriver =  driverFactory.create();
		WebDriverHolder.value(webDriver);
		return webDriver;
	}

	public PageObjectBuilder createPageObjectBuilder() {
		return innerFactory.createPageObjectBuilder();
	}

	public void setRepository(PageObjectRepository value) {
		innerFactory.setRepository(value);
	}

	public Browser getBrowser() {
		return innerFactory.getBrowser();
	}

	public SerialPageObjectBuilderI createSerialPageObjectBuilder() {
		return innerFactory.createSerialPageObjectBuilder();
	}

	public PageObjectRepository getRepository() {
		return innerFactory.getRepository();
	}

	public StatePageObject getStateObject() {
		return innerFactory.getStateObject();
	}

}
