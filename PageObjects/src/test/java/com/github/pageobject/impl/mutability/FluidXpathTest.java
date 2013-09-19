package com.github.pageobject.impl.mutability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.pageobject.impl.browser.Browser;

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
