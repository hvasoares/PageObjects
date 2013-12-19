package com.github.hvasoares.pageobjects.impl.mutability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityFactory;

public class MutableReadabilityTest {

	private MutableReadability instance;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private ReadabilityFactory readabilityFactory;
	@Mock private FluidXpathFactoryI xpathFactory;
	@Mock private Readability readability;

	@Test
	public void shouldReadOneString() {
		instance = new MutableReadability(readabilityFactory,xpathFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(xpathFactory).create("//xpath[:placeH1 = :placeH2]");
			will(returnValue(new FluidXpath("//xpath[:placeH1 = :placeH2]")));
			oneOf(readabilityFactory).create();
			will(returnValue(readability));
			
			oneOf(readability).setProperty("someAlias","//xpath[@value1 = value2]");
			oneOf(readability).read("someAlias");
			will(returnValue("valueReturnedFromFluidXpathElement"));
		}});
				
		instance.addReadProperty("someAlias","//xpath[:placeH1 = :placeH2]");
		
		assertEquals(
			"valueReturnedFromFluidXpathElement",
			instance.read("someAlias",
				"placeH1",	"@value1",
				"placeH2",	"value2"
			)
		);
	}
	
	@Test
	public void shouldReadListOfString() {
		instance = new MutableReadability(readabilityFactory,xpathFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(xpathFactory).create("//xpath[:placeH1 = :placeH2 and position()=:positioningMarker]");
			will(returnValue(new FluidXpath("//xpath[:placeH1 = :placeH2 and position()=:positioningMarker]")));
			exactly(4).of(readabilityFactory).create();
			will(returnValue(readability));
			
			oneOf(readability).setProperty("someAlias","//xpath[@value1 = value2 and position()=1]");
			oneOf(readability).setProperty("someAlias","//xpath[@value1 = value2 and position()=2]");
			oneOf(readability).setProperty("someAlias","//xpath[@value1 = value2 and position()=3]");
			oneOf(readability).setProperty("someAlias","//xpath[@value1 = value2 and position()=4]");
			
			exactly(4).of(readability).read("someAlias");
			will(onConsecutiveCalls(
				returnValue("value1"),
				returnValue("value2"),
				returnValue("value3"),
				throwException(new NoSuchElementException("some reason"))
			));
		}});
				
		instance.addReadProperty("someAlias","//xpath[:placeH1 = :placeH2 and :positioningMarker]");

		List<String> result = instance.readAsList("someAlias",
			"placeH1",	"@value1",
			"placeH2",	"value2"
		);
		
		assertTrue(result.containsAll(Arrays.asList("value1","value2","value3")));
		assertEquals(result.size(),3);
		
	}

}
