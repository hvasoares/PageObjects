package com.github.hvasoares.pageobjects.dsl;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.StatePageObject;

public class PageObjectTestDSLTest {

	private PageObjectTestDSL instance;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private AbstractFactory factory;

	@Test
	public void shouldDecideBetweenMutabilityOrPlainClick() {
		instance = new PageObjectTestDSL();
		instance.setFactory(factory);
		
		ctx.checking(new Expectations(){{
			StatePageObject stateObject = ctx.mock(StatePageObject.class);
			atLeast(2).of(factory).getStateObject(); will(returnValue(stateObject));
			
			oneOf(stateObject).click("someAlias");
			
			oneOf(stateObject).mutability();
			Mutability mutability = ctx.mock(Mutability.class);
			will(returnValue(mutability));
			
			oneOf(mutability).click(
					"anotherAlias",
					"placeHolder1", "value1",
					"placeHolder2", "value2"
			);
		}});
		
		instance.click("someAlias");
		instance.click(
				"anotherAlias",
				"placeHolder1", "value1",
				"placeHolder2", "value2"
		);

	}
	
	@Test
	public void shouldDecideBetweenMutabilityOrPlainFill() {
		instance = new PageObjectTestDSL();
		instance.setFactory(factory);
		
		ctx.checking(new Expectations(){{
			StatePageObject stateObject = ctx.mock(StatePageObject.class);
			atLeast(2).of(factory).getStateObject(); will(returnValue(stateObject));
			
			oneOf(stateObject).fill("someAlias","someValue");
			
			oneOf(stateObject).mutability();
			Mutability mutability = ctx.mock(Mutability.class);
			will(returnValue(mutability));
			
			oneOf(mutability).fill(
					"anotherAlias",	"anotherValue",
					"placeHolder1", "value1",
					"placeHolder2", "value2"
			);
		}});
		
		instance.fill("someAlias","someValue");
		instance.fill(
				"anotherAlias", "anotherValue",
				"placeHolder1", "value1",
				"placeHolder2", "value2"
		);

	}
	

}
