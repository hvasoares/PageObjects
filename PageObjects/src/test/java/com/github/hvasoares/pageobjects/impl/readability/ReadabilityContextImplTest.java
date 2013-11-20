package com.github.hvasoares.pageobjects.impl.readability;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.StatePageObjectSymbolTable;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityContextImpl;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ReadabilityContextImplTest {

	private MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> russianDoll;
	private ReadabilityContextImpl instance;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private StatePageObject realObject;
	private StatePageObject result;
	@Mock private Readability readability;

	@Test
	public void shouldChangeTheReadabilityGivenThePageName() {
		russianDoll = new MatryoshkaDollFactory<StatePageObject,ProxyStatePageObjectAdapter>();
		
		instance = new ReadabilityContextImpl();
		result = russianDoll.create(realObject, instance);
	
		ctx.checking(new Expectations(){{
			oneOf(realObject).setState("somePage");
		}});
		
		instance.add("somePage", readability);
		instance.setState("somePage");
	
		
		assertEquals(instance.readability(),readability);
		
	}

}
