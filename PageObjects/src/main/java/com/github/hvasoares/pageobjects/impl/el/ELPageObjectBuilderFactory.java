package com.github.hvasoares.pageobjects.impl.el;

import org.apache.commons.jexl2.MapContext;

import com.github.hvasoares.pageobjects.DefaultFactory;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.RepositoryAwareFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.PageObjectFactoryImpl;
import com.github.hvasoares.pageobjects.impl.PageObjectImpl;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.AssertivenessImpl;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.ClickableContainerImpl;
import com.github.hvasoares.pageobjects.impl.field.FieldContainerImpl;
import com.github.hvasoares.pageobjects.impl.field.FieldFactoryImpl;
import com.github.hvasoares.pageobjects.impl.field.file.FileFieldFactoryImpl;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityImplementationFactory;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;
import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

public class ELPageObjectBuilderFactory{

	private ElContextImpl elContext;
	private RepositoryAwareFactory factory;
	private SerialPageObjectBuilderI serialPageObjectBuilder;
	public ELPageObjectBuilderFactory(PageObjectRepository repo){
		factory = new DefaultFactory(repo);
	//	repo.setBuilderFactory(this);
	}

	
	public PageObjectBuilder createPageObjectBuilder() {
		return new PageObjectFactoryImpl(
				new ElFieldFactory(
						getElContext(),
						new FieldFactoryImpl(
								getBrowser(), 
								getStateObject(),
								new FileFieldFactoryImpl(getBrowser())
						)
		)).startBuild( 
				new PageObjectImpl(
					new ClickableContainerImpl(),
					new FieldContainerImpl(),
					new AssertivenessImpl(factory.getWebDriver())
				)		
		);
	}

	
	public Browser getBrowser() {
		return factory.getBrowser();
	}

	
	public StatePageObject getStateObject() {
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> m = new MatryoshkaDollFactory<StatePageObject,ProxyStatePageObjectAdapter>();
		return m.create(
				factory.getStateObject(),
				ReadabilityImplementationFactory.createReadabilityStatePageObject(),
				getElContext()
		);
	}
	
	private ElContextImpl getElContext(){
		if(elContext!=null)
			return elContext;
		elContext = new ElContextImpl(
				new MapContext(),
				new JexlExpressionFactoryImpl()
		);
		return elContext;
	}
}
