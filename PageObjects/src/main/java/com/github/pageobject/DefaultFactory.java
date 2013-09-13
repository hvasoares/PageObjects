package com.github.pageobject;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.PageObjectBuilderSymbolTable;
import com.github.pageobject.impl.PageObjectFactoryImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.SerialPageObjectBuilder;
import com.github.pageobject.impl.StatePageObjectImpl;
import com.github.pageobject.impl.StatePageObjectSymbolTable;
import com.github.pageobject.impl.assertivepageobject.AssertivenessImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.browser.BrowserImpl;
import com.github.pageobject.impl.browser.RetryBrowser;
import com.github.pageobject.impl.el.ElFactory;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.field.file.FileFieldFactoryImpl;
import com.github.pageobject.impl.readability.ReadabilityImplementationFactory;
import com.github.pageobject.impl.webdriver.FirefoxWebDriverFactory;
import com.github.pageobject.impl.webdriver.WebDriverFactory;
import com.github.pageobject.proxy.MatryoshkaDollFactory;
import com.github.pageobject.runner.PageObjectRepository;

public class DefaultFactory implements AbstractFactory{
	private StatePageObjectSymbolTable state;
	private Browser browser;
	private PageObjectRepository repository;
	private WebDriver driver;
	private SerialPageObjectBuilder serialBuilder;

	public DefaultFactory(PageObjectRepository repository) {
		this.repository = repository;
		repository.setBuilderFactory(this);
	}

	@Override
	public PageObjectBuilder createPageObjectBuilder(){
		MatryoshkaDollFactory<PageObjectBuilderSymbolTable, ProxyPageObjectBuilderAdapter> m = new MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter>();
		PageObjectBuilderSymbolTable result = m.create(
				new PageObjectFactoryImpl(
						ElFactory.createFieldFactory(
							new FieldFactoryImpl(
								getBrowser(),
								getStateObject(),
								new FileFieldFactoryImpl(getBrowser())
							)
						)
				),
				ReadabilityImplementationFactory.createReadabilityBuilder(getWebDriver())
		);
		return result.startBuild( 
				new PageObjectImpl(
						new ClickableContainerImpl(),
						new FieldContainerImpl(),
						new AssertivenessImpl(getWebDriver())
				)
		);
	}
	
	@Override
	public StatePageObjectSymbolTable getStateObject(){
		if(this.state!=null)
			return state;
		MatryoshkaDollFactory<StatePageObjectSymbolTable, ProxyStatePageObjectAdapter> m = new MatryoshkaDollFactory<StatePageObjectSymbolTable,ProxyStatePageObjectAdapter>();
		state= m.create(
				new StatePageObjectImpl(repository),
				ReadabilityImplementationFactory.createReadabilityStatePageObject(),
				ElFactory.createElContextStatePageObject()
		);
		return state;
	}
	
	@Override
	public Browser getBrowser(){
		if(this.browser!=null)
			return browser;
		browser =new RetryBrowser(new BrowserImpl(getWebDriver()));
		return browser;
	}

	@Override
	public SerialPageObjectBuilder createSerialPageObjectBuilder() {
		if(serialBuilder!=null)
			return serialBuilder;
		serialBuilder = new SerialPageObjectBuilder(this);
		return serialBuilder;
	}
	public WebDriver getWebDriver(){
		if(driver!=null)
			return driver;
		WebDriverFactory factory = new FirefoxWebDriverFactory();
		driver = factory.create();
		return driver;
	}
}
