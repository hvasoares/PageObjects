package com.github.pageobject.impl.readability;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.PageObjectBuilderSymbolTable;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.proxy.MatryoshkaDollFactory;

public class ReadabilityBuilderTest {

	private MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter> russianDoll;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}}; 
	@Mock private PageObjectBuilderSymbolTable realObject;
	@Mock private ReadabilityContext readabilitCtx;

	private PageObjectBuilderSymbolTable result;

	private WebDriver webdriver;

	@Test
	public void shouldSaveCreateReabilityEveryTimeAPageGetsName() {
		russianDoll = new MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter>();
		ReadabilityBuilder instance = new ReadabilityBuilder(webdriver);
		
		instance.setReadabilitCtx(readabilitCtx);
		result = russianDoll.create(realObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(readabilitCtx).add(with("somePageName"), with(aNonNull(ReadabilityImpl.class)));
			oneOf(realObject).setName("somePageName");
			oneOf(realObject).addTextField("field", "someXpath");
		}});
		
		result.setName("somePageName");
		result.addTextField("field","someXpath");
		assertEquals(
				instance.getCurrent().getDb().get("field"),"someXpath"
		);
		assertEquals(instance.getCurrent(),result.readability());
	}

}
