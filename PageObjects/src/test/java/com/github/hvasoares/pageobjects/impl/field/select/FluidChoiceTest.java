package com.github.hvasoares.pageobjects.impl.field.select;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.select.FluidChoice;

public class FluidChoiceTest {

	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	private FluidChoice instance;
	@Mock 
	private Browser browser;

	@Test
	public void shouldGenerateTheXpathGivenTheValueToBeFilled() {
		instance = new FluidChoice(
			"someAlias",
			"//xpathToSelect"
		);
		
		instance.setBrowser(
			browser
		);
		
		ctx.checking(new Expectations(){{
			oneOf(browser).click("//xpathToSelect//option[normalize-space(text())='someValue']");
		}});
		
		instance.fill("someValue");
		
		ctx.assertIsSatisfied();
	}

}
