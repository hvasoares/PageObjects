package com.github.hvasoares.pageobjects.impl.mutability;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.impl.PageObjectBuilderSymbolTable;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;
import com.github.hvasoares.pageobjects.proxy.DecoratorObject;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ProxyPageBuilderTest {
	private MatryoshkaDollFactory<PageObjectBuilderSymbolTable, ProxyPageObjectBuilderAdapter> m;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private PageObjectBuilderSymbolTable realObject;
	@Mock private PageObjectBuilderSymbolTable outerPageBuilder;
	@Mock private PageContextI context;
	@Mock private Mutability mutability;
	@Mock private DecoratorObject<PageObjectBuilderSymbolTable> outerStateObject;

	@Test
	public void shouldCreateContextJustANameIsCreated() {
		m = new MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter>();
		ProxyPageBuilder instance = new ProxyPageBuilder(context);
		instance.setOuter(outerStateObject);
		PageObjectBuilderSymbolTable doll = m.create(realObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(realObject).setName("someName");
			oneOf(outerStateObject).getOuter();
			will(returnValue(outerPageBuilder));
			oneOf(context).createMutability("someName",outerPageBuilder);
			will(returnValue(mutability));
		}});
		
		doll.setName("someName");
		assertEquals(instance.mutability(),mutability);
	}

}
