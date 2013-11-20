package com.github.pageobject;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.ActualFieldFactoryGetter;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.LazyFieldFactory;
import com.github.pageobject.impl.PageObjectBuilderSymbolTable;
import com.github.pageobject.impl.PageObjectFactoryImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.SerialPageObjectBuilder;
import com.github.pageobject.impl.StatePageObjectImpl;
import com.github.pageobject.impl.assertivepageobject.AssertivenessFactory;
import com.github.pageobject.impl.assertivepageobject.AssertivenessImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.browser.BrowserFactory;
import com.github.pageobject.impl.browser.BrowserImpl;
import com.github.pageobject.impl.el.ElFactory;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.field.WebDriverAwareCustomFieldFactory;
import com.github.pageobject.impl.field.file.FileFieldFactoryImpl;
import com.github.pageobject.impl.logging.LoggingFactory;
import com.github.pageobject.impl.mutability.MutabilityImplementationFactory;
import com.github.pageobject.impl.readability.ReadabilityImplementationFactory;
import com.github.pageobject.impl.webdriver.FirefoxWebDriverFactory;
import com.github.pageobject.impl.webdriver.WebDriverFactory;
import com.github.pageobject.proxy.MatryoshkaDollFactory;
import com.github.pageobject.runner.PageObjectRepository;

public class DefaultFactory implements RepositoryAwareFactory, ActualFieldFactoryGetter{
	private StatePageObject state;
	private Browser browser;
	private PageObjectRepository repository;
	private WebDriver driver;
	private SerialPageObjectBuilderI serialBuilder;
	private FieldFactory fieldFactory;
	
	public DefaultFactory(){}
	
	public DefaultFactory(PageObjectRepository repository) {
		setRepository(repository);
	}

	@Override
	public PageObjectBuilder createPageObjectBuilder(){
		MatryoshkaDollFactory<PageObjectBuilderSymbolTable, ProxyPageObjectBuilderAdapter> m = new MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter>();
		PageObjectBuilderSymbolTable result = m.create(
				new PageObjectFactoryImpl(
						getLazyFieldFactory()
				),
				ReadabilityImplementationFactory.createReadabilityBuilder(getWebDriver()),
				MutabilityImplementationFactory.createPageBuilder(getLazyFieldFactory())
		);
		return result.startBuild( 
				new PageObjectImpl(
						new ClickableContainerImpl(),
						new FieldContainerImpl(),
						AssertivenessFactory.create(getWebDriver())
				)
		);
	}

	private FieldFactory getLazyFieldFactory() {
		return new LazyFieldFactory(this);
	}
	
	@Override
	public StatePageObject getStateObject(){
		if(this.state!=null)
			return state;
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> m = new MatryoshkaDollFactory<StatePageObject,ProxyStatePageObjectAdapter>();
		state= m.create(
				new StatePageObjectImpl(repository),
				LoggingFactory.createStatePageObjectLogging(),
				ReadabilityImplementationFactory.createReadabilityStatePageObject(),
				ElFactory.createElContextStatePageObject(),
				MutabilityImplementationFactory.createStatePageObject(getLazyFieldFactory())
		);
		return state;
	}
	
	@Override
	public Browser getBrowser(){
		if(this.browser!=null)
			return browser;
		browser = LoggingFactory.createBrowserLogging(
			BrowserFactory.createBrowser(getWebDriver())
		);
		return browser;
	}

	@Override
	public SerialPageObjectBuilderI createSerialPageObjectBuilder() {
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

	@Override
	public FieldFactory getFieldFactory() {
		fieldFactory= ElFactory.createFieldFactory(
				new WebDriverAwareCustomFieldFactory(
					new FieldFactoryImpl(
						getBrowser(),
						getStateObject(),
						new FileFieldFactoryImpl(getBrowser())
					),
					getWebDriver()
				)
			);
		return fieldFactory;
	}

	@Override
	public void setRepository(PageObjectRepository value) {
		this.repository = value;
		repository.setBuilderFactory(this);
		serialBuilder = null;
		fieldFactory =null;
		state = null;
	}
	
}
