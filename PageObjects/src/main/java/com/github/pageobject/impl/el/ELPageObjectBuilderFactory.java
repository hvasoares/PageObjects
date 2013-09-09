package com.github.pageobject.impl.el;

import org.apache.commons.jexl2.MapContext;

import com.github.pageobject.AbstractFactory;
import com.github.pageobject.DefaultFactory;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.PageObjectFactoryImpl;
import com.github.pageobject.impl.PageObjectImpl;
import com.github.pageobject.impl.SerialPageObjectBuilder;
import com.github.pageobject.impl.assertivepageobject.AssertivePageObjectImpl;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.ClickableContainerImpl;
import com.github.pageobject.impl.field.FieldContainerImpl;
import com.github.pageobject.impl.field.FieldFactoryImpl;
import com.github.pageobject.impl.field.file.FileFieldFactoryImpl;
import com.github.pageobject.runner.PageObjectRepository;

public class ELPageObjectBuilderFactory implements AbstractFactory{

	private ElContext elContext;
	private DefaultFactory factory;
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
		)).startBuild( new AssertivePageObjectImpl(
				new PageObjectImpl(
					new ClickableContainerImpl(),
					new FieldContainerImpl()
				),
				factory.getWebDriver()
		));
	}

	@Override
	public SerialPageObjectBuilder createSerialPageObjectBuilder() {
		return new SerialPageObjectBuilder(this);
	}

	@Override
	public Browser getBrowser() {
		return factory.getBrowser();
	}

	@Override
	public StatePageObject getStateObject() {
		return getElContext();
	}
	
	private ElContext getElContext(){
		if(elContext!=null)
			return elContext;
		elContext = new ElContextImpl(
				factory.getStateObject(),
				new MapContext(),
				new JexlExpressionFactoryImpl()
		);
		return elContext;
	}
}
