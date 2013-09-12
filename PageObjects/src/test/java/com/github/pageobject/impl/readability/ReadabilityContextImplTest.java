package com.github.pageobject.impl.readability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.Readability;
import com.github.pageobject.impl.StatePageObjectSymbolTable;
import com.github.pageobject.proxy.MatryoshkaDollFactory;

public class ReadabilityContextImplTest {

	private MatryoshkaDollFactory<StatePageObjectSymbolTable, ProxyStatePageObjectAdapter> russianDoll;
	private ReadabilityContextImpl instance;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private StatePageObjectSymbolTable realObject;
	private StatePageObjectSymbolTable result;
	@Mock private Readability readability;

	@Test
	public void shouldChangeTheReadabilityGivenThePageName() {
		russianDoll = new MatryoshkaDollFactory<StatePageObjectSymbolTable,ProxyStatePageObjectAdapter>();
		
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
