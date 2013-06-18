package com.github.pageobject;

import com.github.pageobject.impl.PageObjectBuilderImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.SerialPageObjectBuilderImpl;
import com.github.pageobject.impl.SinglePageObjectBuilder;
import com.github.pageobject.impl.StatePageObjectImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.browser.BrowserImpl;
import com.github.pageobject.impl.browser.RetryBrowser;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.webdriver.FirefoxWebDriverFactory;
import com.github.pageobject.impl.webdriver.WebDriverFactory;
import com.github.pageobject.runner.PageObjectRepository;

public class DefaultFactory implements PageObjectBuilderFactory{
	private StatePageObjectImpl state;
	private Browser browser;
	private PageObjectRepository repository;

	public DefaultFactory(PageObjectRepository repository) {
		this.repository = repository;
		repository.setBuilderFactory(this);
	}

	@Override
	public SinglePageObjectBuilder createSinglePageObjectBuilder(){
		PageObjectBuilderImpl result = new PageObjectBuilderImpl(
				new FieldFactoryImpl(
						getBrowser(),
						getStateObject()
					)
		);
		return result.startBuild(new PageObjectImpl(
				new ClickableContainerImpl(),
				new FieldContainerImpl()
		));
	}
	
	public StatePageObject getStateObject(){
		if(this.state==null)
			state = new StatePageObjectImpl(repository);
		return state;
	}
	
	public Browser getBrowser(){
		if(this.browser!=null)
			return browser;
		WebDriverFactory factory = new FirefoxWebDriverFactory();
		browser =new RetryBrowser(new BrowserImpl(factory.create()));
		return browser;
	}

	@Override
	public SerialPageObjectBuilder createSerialPageObjectBuilder() {
		return new SerialPageObjectBuilderImpl(this);
	}
}
