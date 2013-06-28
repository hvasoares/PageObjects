package com.github.pageobject;

import com.github.pageobject.impl.PageObjectFactoryImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.SerialPageObjectBuilder;
import com.github.pageobject.impl.StatePageObjectImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.browser.BrowserImpl;
import com.github.pageobject.impl.browser.RetryBrowser;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.field.file.FileFieldFactoryImpl;
import com.github.pageobject.impl.webdriver.FirefoxWebDriverFactory;
import com.github.pageobject.impl.webdriver.WebDriverFactory;
import com.github.pageobject.runner.PageObjectRepository;

public class DefaultFactory implements AbstractFactory{
	private StatePageObjectImpl state;
	private Browser browser;
	private PageObjectRepository repository;

	public DefaultFactory(PageObjectRepository repository) {
		this.repository = repository;
		repository.setBuilderFactory(this);
	}

	@Override
	public PageObjectBuilder createPageObjectBuilder(){
		PageObjectFactoryImpl result = new PageObjectFactoryImpl(
				new FieldFactoryImpl(
						getBrowser(),
						getStateObject(),
						new FileFieldFactoryImpl(getBrowser())
					)
		);
		return result.startBuild(new PageObjectImpl(
				new ClickableContainerImpl(),
				new FieldContainerImpl()
		));
	}
	
	@Override
	public StatePageObject getStateObject(){
		if(this.state==null)
			state = new StatePageObjectImpl(repository);
		return state;
	}
	
	@Override
	public Browser getBrowser(){
		if(this.browser!=null)
			return browser;
		WebDriverFactory factory = new FirefoxWebDriverFactory();
		browser =new RetryBrowser(new BrowserImpl(factory.create()));
		return browser;
	}

	@Override
	public SerialPageObjectBuilder createSerialPageObjectBuilder() {
		return new SerialPageObjectBuilder(this);
	}
}
