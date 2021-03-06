package com.github.hvasoares.pageobjects.impl.el;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.apache.commons.jexl2.JexlContext;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.impl.StatePageObjectSymbolTable;
import com.github.hvasoares.pageobjects.impl.el.ElContextImpl;
import com.github.hvasoares.pageobjects.impl.el.JexlExpressionFactory;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ElContextImplTest {

	private ElContextImpl inst;
	@Mock private StatePageObject stateMachine;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private JexlContext mapContext;
	@Mock private JexlExpressionFactory unifiedEl;
	private MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> russianDoll;
	@Mock private StatePageObject realObject;
	private StatePageObject result;

	@Test
	public void givenAPageShouldAssignItFieldsToAMap() {
		inst = new ElContextImpl(
				mapContext,
				unifiedEl
		);
		
		russianDoll = new MatryoshkaDollFactory<StatePageObject,ProxyStatePageObjectAdapter>();
		result = russianDoll.create(realObject, inst);
		
		ctx.checking(new Expectations(){{
			oneOf(realObject).setState("somePage");
			oneOf(realObject).fill("someField","someValueEvaluated");
			oneOf(mapContext).set(
					with(is("somePage")),
					with(any(HashMap.class))
			);
			atLeast(2).of(unifiedEl).getResult(mapContext,"someValue");
			will(returnValue("someValueEvaluated"));
		}});
		
		result.setState("somePage");
		result.fill("someField","someValue");
		
		ctx.assertIsSatisfied();
		
		assertEquals(inst.getCurrentMap().get("someField"),"someValueEvaluated");
	}

}