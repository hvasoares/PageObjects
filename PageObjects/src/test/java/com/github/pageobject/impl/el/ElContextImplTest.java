package com.github.pageobject.impl.el;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.commons.jexl2.JexlContext;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import com.github.pageobject.StatePageObject;

public class ElContextImplTest {

	private ElContextImpl inst;
	private StatePageObject stateMachine;
	private Mockery ctx;
	private JexlContext mapContext;
	private JexlExpressionFactory unifiedEl;

	@Test
	public void givenAPageShouldAssignItFieldsToAMap() {
		ctx = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		stateMachine = ctx.mock(StatePageObject.class);
		mapContext = ctx.mock(JexlContext.class);
		unifiedEl = ctx.mock(JexlExpressionFactory.class);
		inst = new ElContextImpl(
				stateMachine,
				mapContext,
				unifiedEl
		);
		
		ctx.checking(new Expectations(){{
			oneOf(stateMachine).setState("somePage");
			oneOf(stateMachine).fill("someField","someValueEvaluated");
			oneOf(mapContext).set(
					with(is("somePage")),
					with(any(HashMap.class))
			);
			atLeast(2).of(unifiedEl).getResult(mapContext,"someValue");
			will(returnValue("someValueEvaluated"));
		}});
		
		inst.setState("somePage");
		inst.fill("someField","someValue");
		
		ctx.assertIsSatisfied();
		
		assertEquals(inst.getCurrentMap().get("someField"),"someValueEvaluated");
	}

}