package com.github.pageobject.impl.el;

import org.apache.commons.jexl2.MapContext;

import com.github.pageobject.AbstractFactory;
import com.github.pageobject.DefaultFactory;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.PageObjectFactoryImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.SerialPageObjectBuilder;
import com.github.pageobject.impl.StatePageObjectSymbolTable;
import com.github.pageobject.impl.assertivepageobject.AssertivenessImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.field.file.FileFieldFactoryImpl;
import com.github.pageobject.impl.readability.ReadabilityImplementationFactory;
import com.github.pageobject.proxy.MatryoshkaDollFactory;
import com.github.pageobject.runner.PageObjectRepository;

public class ELPageObjectBuilderFactory implements AbstractFactory{

	private ElContextImpl elContext;
	private DefaultFactory factory;
	private SerialPageObjectBuilder serialPageObjectBuilder;
	public ELPageObjectBuilderFactory(PageObjectRepository repo){
		factory = new DefaultFactory(repo);
		repo.setBuilderFactory(this);
	}

	@Override
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

	@Override
	public SerialPageObjectBuilder createSerialPageObjectBuilder() {
		if(serialPageObjectBuilder!=null)
			return serialPageObjectBuilder;
		this.serialPageObjectBuilder= new SerialPageObjectBuilder(this);
		return serialPageObjectBuilder;
	}

	@Override
	public Browser getBrowser() {
		return factory.getBrowser();
	}

	@Override
	public StatePageObject getStateObject() {
		MatryoshkaDollFactory<StatePageObjectSymbolTable, ProxyStatePageObjectAdapter> m = new MatryoshkaDollFactory<StatePageObjectSymbolTable,ProxyStatePageObjectAdapter>();
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
