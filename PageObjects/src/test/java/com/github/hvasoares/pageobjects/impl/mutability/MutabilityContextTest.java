package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.mutability.FluidXpath;
import com.github.hvasoares.pageobjects.impl.mutability.MutabilityContext;
import com.github.hvasoares.pageobjects.impl.mutability.MutabilityContextI;

public class MutabilityContextTest {

	private MutabilityContextI instance;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private FieldFactory fieldFactory;
	@Mock private Clickable click;

	@Test
	public void shouldCreateAClickableWithTransitions() {
		instance = new MutabilityContext(fieldFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(fieldFactory).createClickable("someAlias", "//fluidXpath//isThis","toPage");
			will(returnValue(click));
			oneOf(click).click();
		}});
		
		instance.add("someAlias", new FluidXpath("//:placeH1//:placeH2"),"toPage");
		instance.click("someAlias",
					Arrays.asList(
						"placeH1",	"fluidXpath",
						"placeH2",	"isThis"
					).toArray(new String[4])
		);
	}
	
	@Test
	public void shouldCreateAClickableWithNoTransitions() {
		instance = new MutabilityContext(fieldFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(fieldFactory).createClickable("someAlias", "//fluidXpath//isThis");
			will(returnValue(click));
			
			oneOf(click).click();
		}});
		
		instance.add("someAlias", new FluidXpath("//:placeH1//:placeH2"));
		instance.click("someAlias",
					Arrays.asList(
						"placeH1",	"fluidXpath",
						"placeH2",	"isThis"
					).toArray(new String[4])
		);
	}

}
