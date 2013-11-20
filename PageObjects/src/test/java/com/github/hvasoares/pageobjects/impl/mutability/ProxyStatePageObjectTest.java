package com.github.hvasoares.pageobjects.impl.mutability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.impl.StatePageObjectSymbolTable;
import com.github.hvasoares.pageobjects.impl.mutability.MultiArgsClickable;
import com.github.hvasoares.pageobjects.impl.mutability.PageContextI;
import com.github.hvasoares.pageobjects.impl.mutability.ProxyStatePageObject;
import com.github.hvasoares.pageobjects.proxy.DecoratorObject;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ProxyStatePageObjectTest {

	@Mock private PageContextI context;
	private MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> m;
	@Mock private StatePageObjectSymbolTable realObject;
	@Mock private MultiArgsClickable mutability; 
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private StatePageObject outerStateObject;


	private ProxyStatePageObject instance;
	@Mock private DecoratorObject<StatePageObject> outerDecorator;

	@Test
	public void shouldSetTheCurrentMutability() {
		instance = new ProxyStatePageObject(context);
		m = new MatryoshkaDollFactory<StatePageObject,ProxyStatePageObjectAdapter>();
		
		StatePageObject doll = m.create(realObject, instance);
		instance.setOuter(outerDecorator);
		ctx.checking(new Expectations(){{
			oneOf(outerDecorator).getOuter();
			will(returnValue(outerStateObject));
			oneOf(context).get("newState",outerStateObject);
			will(returnValue(mutability));
			oneOf(realObject).setState("newState");
		}});
		
		instance.setState("newState");
		
		assertEquals(doll.mutability(),mutability);
	}

}
