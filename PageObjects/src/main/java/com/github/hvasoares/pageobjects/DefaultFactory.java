package com.github.hvasoares.pageobjects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.impl.ActualFieldFactoryGetter;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.LazyFieldFactory;
import com.github.hvasoares.pageobjects.impl.PageObjectBuilderSymbolTable;
import com.github.hvasoares.pageobjects.impl.PageObjectFactoryImpl;
import com.github.hvasoares.pageobjects.impl.PageObjectImpl;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.impl.SerialPageObjectBuilder;
import com.github.hvasoares.pageobjects.impl.StatePageObjectImpl;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.AssertivenessFactory;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.AssertivenessImpl;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.browser.BrowserFactory;
import com.github.hvasoares.pageobjects.impl.browser.BrowserImpl;
import com.github.hvasoares.pageobjects.impl.el.ElFactory;
import com.github.hvasoares.pageobjects.impl.field.ClickableContainerImpl;
import com.github.hvasoares.pageobjects.impl.field.FieldContainerImpl;
import com.github.hvasoares.pageobjects.impl.field.FieldFactoryImpl;
import com.github.hvasoares.pageobjects.impl.field.WebDriverAwareCustomFieldFactory;
import com.github.hvasoares.pageobjects.impl.field.file.FileFieldFactoryImpl;
import com.github.hvasoares.pageobjects.impl.logging.LoggingFactory;
import com.github.hvasoares.pageobjects.impl.mutability.MutabilityImplementationFactory;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityImplementationFactory;
import com.github.hvasoares.pageobjects.impl.webdriver.FirefoxWebDriverFactory;
import com.github.hvasoares.pageobjects.impl.webdriver.WebDriverFactory;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;
import com.github.hvasoares.pageobjects.report.ReportFactory;
import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

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
				ReportFactory.createReportedStatePageObject(),
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
 		WebDriverHolder.value(driver);
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
	
	@Override
	public PageObjectRepository getRepository() {
		return repository;
	}
	
}
