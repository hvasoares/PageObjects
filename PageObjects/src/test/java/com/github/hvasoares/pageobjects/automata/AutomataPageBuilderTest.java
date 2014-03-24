package com.github.hvasoares.pageobjects.automata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.PageObjectBuilderSymbolTable;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class AutomataPageBuilderTest {

	private AutomataPageBuilder instance;
	@Mock private PageObjectBuilderSymbolTable innerObject;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private AutomataImpl automata;
	@Mock private AutomataFieldFillerImpl fieldFiller;

	@Test
	public void shouldCreateAutomataBeforeItsFirstUse() {
		instance = new AutomataPageBuilder(automata,fieldFiller);
		MatryoshkaDollFactory<PageObjectBuilderSymbolTable,ProxyPageObjectBuilderAdapter> dollFactory = 
				new MatryoshkaDollFactory<>();
		
		PageObjectBuilderSymbolTable doll = dollFactory.create(innerObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(automata).setFieldFiller(fieldFiller);
			oneOf(automata).setBuilder(innerObject);
		}});
		
		assertNotNull(doll.automata());
		assertEquals(doll.automata(),automata);
	}

}
