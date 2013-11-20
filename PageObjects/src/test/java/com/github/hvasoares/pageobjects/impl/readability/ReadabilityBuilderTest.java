package com.github.hvasoares.pageobjects.impl.readability;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.impl.PageObjectBuilderSymbolTable;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;
import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityBuilder;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityContext;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityImpl;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ReadabilityBuilderTest {

	private MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter> russianDoll;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}}; 
	@Mock private PageObjectBuilderSymbolTable realObject;
	@Mock private ReadabilityContext readabilitCtx;
	@Mock private Readability previousReadability;

	private PageObjectBuilderSymbolTable result;

	private WebDriver webdriver;

	@Test
	public void shouldSaveCreateReabilityEveryTimeAPageGetsName() {
		russianDoll = new MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter>();
		ReadabilityBuilder instance = new ReadabilityBuilder(webdriver);
		
		instance.setReadabilitCtx(readabilitCtx);
		result = russianDoll.create(realObject, instance);
		
		ctx.checking(new Expectations(){{
			atLeast(2).of(readabilitCtx).get("somePageName");
			will(onConsecutiveCalls(returnValue(null),returnValue(previousReadability)));
			
			oneOf(readabilitCtx).add(with("somePageName"), with(aNonNull(ReadabilityImpl.class)));
			
			atLeast(2).of(realObject).setName("somePageName");
			oneOf(realObject).addTextField("field", "someXpath");
		}});
		
		result.setName("somePageName");
		result.addTextField("field","someXpath");
		assertEquals(
				instance.getCurrent().getDb().get("field"),"someXpath"
		);
		
		assertEquals(instance.getCurrent(),result.readability());
		
		result.setName("somePageName");
		
		assertEquals(result.readability(),previousReadability);
	}

}
