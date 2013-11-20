package com.github.hvasoares.pageobjects.impl.mutability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.mutability.FluidXpath;
import com.github.hvasoares.pageobjects.impl.mutability.FluidXpathI;

public class FluidXpathTest {

	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Mock private Browser browser;

	private FluidXpathI instance;

	@Test
	public void shouldSendToBrowserXpathWithPlaceHolder() {
		instance = new FluidXpath("//someXpath/:placeH1/:placeH2");
		
		instance.bind("placeH1","value1");
		instance.bind("placeH2", "value2");
		
		assertEquals(instance.getTransformedXpath(),"//someXpath/value1/value2");

	}
	

}
