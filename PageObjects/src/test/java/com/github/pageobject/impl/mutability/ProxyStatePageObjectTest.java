package com.github.pageobject.impl.mutability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.StatePageObjectSymbolTable;
import com.github.pageobject.proxy.DecoratorObject;
import com.github.pageobject.proxy.MatryoshkaDollFactory;

public class ProxyStatePageObjectTest {

	@Mock private PageContextI context;
	private MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> m;
	@Mock private StatePageObjectSymbolTable realObject;
	@Mock private Mutability mutability; 
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
